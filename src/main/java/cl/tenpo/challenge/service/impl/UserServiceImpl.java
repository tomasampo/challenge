package cl.tenpo.challenge.service.impl;

import cl.tenpo.challenge.domain.User;
import cl.tenpo.challenge.dto.request.LoginRequest;
import cl.tenpo.challenge.exception.UsernameAlreadyUsedException;
import cl.tenpo.challenge.repository.UserRepository;
import cl.tenpo.challenge.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(LoginRequest loginRequest) {
        if (userRepository.findOneByLogin(loginRequest.getLogin()).isPresent()) {
            throw new UsernameAlreadyUsedException();
        }
        User user = new User();
        user.setLogin(loginRequest.getLogin().toLowerCase());
        String encryptedPassword = passwordEncoder.encode(loginRequest.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return user;
    }
}
