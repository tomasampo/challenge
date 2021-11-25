package cl.tenpo.challenge.service.impl;

import cl.tenpo.challenge.dto.request.LoginRequest;
import cl.tenpo.challenge.dto.response.JWTToken;
import cl.tenpo.challenge.security.jwt.TokenProvider;
import cl.tenpo.challenge.service.AuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static cl.tenpo.challenge.security.SecurityUtils.bearTokenToJwt;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthenticationServiceImpl(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @Override
    public JWTToken login(LoginRequest loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getLogin(),
                loginDTO.getPassword()
        );
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new JWTToken(tokenProvider.createToken(authentication));
    }

    @Override
    public void logout(String bearerToken) {
        Optional<String> jwt = bearTokenToJwt(bearerToken);
        if (jwt.isPresent() && this.tokenProvider.validateToken(jwt.get())) {
            tokenProvider.blacklistedToken(jwt.get());
        }
    }


}