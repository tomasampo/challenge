package cl.tenpo.challenge.security.jwt;

import cl.tenpo.challenge.config.ChallengeProperties;
import cl.tenpo.challenge.domain.TokenBlacklist;
import cl.tenpo.challenge.repository.TokenBlacklistRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private final Key key;

    private final JwtParser jwtParser;

    private final long tokenValidityInMilliseconds;

    private final TokenBlacklistRepository tokenBlacklistRepository;


    public TokenProvider(ChallengeProperties challengeProperties, TokenBlacklistRepository tokenBlacklistRepository) {
        this.tokenBlacklistRepository = tokenBlacklistRepository;
        String secret = challengeProperties.getSecret();
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
        this.tokenValidityInMilliseconds = 1000 * challengeProperties.getTokenValidityInSeconds();
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);
        return Jwts
            .builder()
            .setSubject(authentication.getName())
            .claim(AUTHORITIES_KEY, authorities)
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        Collection<? extends GrantedAuthority> authorities = Arrays
            .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
            .filter(auth -> !auth.trim().isEmpty())
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public TokenBlacklist blacklistedToken(String authToken) {
        TokenBlacklist tokenBlacklist = new TokenBlacklist();
        tokenBlacklist.setToken(authToken);
        tokenBlacklist.setExpiry(jwtParser.parseClaimsJws(authToken).getBody().getExpiration().toInstant());
        return tokenBlacklistRepository.save(tokenBlacklist);
    }

    public boolean validateToken(String authToken) {
        if (tokenBlacklistRepository.findOneByToken(authToken).isPresent()) {
            log.trace("blacklisted JWT token");
            return false;
        }
        try {
            jwtParser.parseClaimsJws(authToken).getBody();
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
            log.trace("Invalid JWT token.", e);
        } catch (IllegalArgumentException e) {
            log.error("Token validation error {}", e.getMessage());
        }
        return false;
    }

    @Scheduled(fixedDelay = 60*60000)
    public void deleteBlacklistExpiredTokens() {
        log.info("Process start remove expired tokens from blacklist");
        tokenBlacklistRepository.deleteAllByExpiryLessThan(Instant.now());
    }
}
