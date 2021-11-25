package cl.tenpo.challenge.service;

import cl.tenpo.challenge.domain.User;
import cl.tenpo.challenge.dto.request.LoginRequest;


public interface UserService {
    User register(LoginRequest loginRequest);
}
