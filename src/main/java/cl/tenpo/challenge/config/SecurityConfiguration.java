package cl.tenpo.challenge.config;

import cl.tenpo.challenge.security.jwt.JWTConfigurer;
import cl.tenpo.challenge.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    private final ChallengeProperties challengeProperties;

    private final TokenProvider tokenProvider;


    public SecurityConfiguration(
            TokenProvider tokenProvider,
            ChallengeProperties challengeProperties
    ) {
        this.tokenProvider = tokenProvider;
        this.challengeProperties = challengeProperties;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers(AUTH_WHITELIST);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
            http
                .csrf()
                .disable()
                .addFilterBefore(this.corsFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .and()
                .headers()
                .contentSecurityPolicy(challengeProperties.getContentSecurityPolicy())
                .and()
                .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
                .and()
                .frameOptions()
                .deny()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/accounts/register").permitAll()
                .antMatchers("/api/auth/login").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .httpBasic()
                .and()
                .apply(securityConfigurerAdapter());
    }

    private CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = challengeProperties.getCors();
        source.registerCorsConfiguration("/api/**", config);
        source.registerCorsConfiguration("/swagger-resources", config);
        source.registerCorsConfiguration("/swagger-ui/**", config);
        return new CorsFilter(source);
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
}
