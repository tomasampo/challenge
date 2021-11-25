package cl.tenpo.challenge.web.controller;

import cl.tenpo.challenge.dto.request.LoginRequest;
import cl.tenpo.challenge.repository.UserRepository;
import cl.tenpo.challenge.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

    private final UserRepository userRepository;

    private final UserService userService;


    public AccountController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody LoginRequest loginRequest) {
       userService.register(loginRequest);
    }

}
