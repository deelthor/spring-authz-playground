package de.deelthor.tokenexample.authzserver.service;

import de.deelthor.tokenexample.authzserver.repository.User;
import de.deelthor.tokenexample.authzserver.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public static final String USER_ROLE = "user";
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private TokenService tokenService;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

}
