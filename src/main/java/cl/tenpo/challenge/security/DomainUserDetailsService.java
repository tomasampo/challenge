package cl.tenpo.challenge.security;

import cl.tenpo.challenge.domain.User;
import cl.tenpo.challenge.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Collections.emptyList;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        return userRepository
                .findOneByLogin(login)
                .map(user -> createSpringSecurityUser(user))
                .orElseThrow(() -> new UsernameNotFoundException("User " + login + " was not found in the database"));
    }
    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), emptyList());
    }
}
