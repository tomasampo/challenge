package cl.tenpo.challenge.service;

import cl.tenpo.challenge.dto.request.LoginRequest;
import cl.tenpo.challenge.dto.response.JWTToken;


public interface AuthenticationService {
    JWTToken login(LoginRequest loginRequest);
    void logout(String token);
}
