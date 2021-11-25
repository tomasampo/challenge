package cl.tenpo.challenge.web.controller;

import cl.tenpo.challenge.dto.request.LoginRequest;
import cl.tenpo.challenge.dto.response.JWTToken;
import cl.tenpo.challenge.security.jwt.JWTFilter;

import javax.validation.Valid;

import cl.tenpo.challenge.service.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthenticateController {

    private final AuthenticationService authenticationService;


    public AuthenticateController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTToken> login(@Valid @RequestBody LoginRequest loginRequest) {
        JWTToken jwt = this.authenticationService.login(loginRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt.getIdToken());
        return new ResponseEntity<>(jwt, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(@RequestHeader("Authorization") String token) {
        this.authenticationService.logout(token);
    }
}
