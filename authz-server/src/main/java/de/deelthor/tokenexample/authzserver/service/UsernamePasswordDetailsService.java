package de.deelthor.tokenexample.authzserver.service;

import de.deelthor.tokenexample.authzserver.repository.Role;
import de.deelthor.tokenexample.authzserver.TokenUserDetails;
import de.deelthor.tokenexample.authzserver.repository.User;
import de.deelthor.tokenexample.authzserver.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsernamePasswordDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private TokenService tokenService;


    public UsernamePasswordDetailsService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            return getUserDetails(userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new));
        } catch (UserNotFoundException ex) {
            throw new UsernameNotFoundException("Account for '" + email + "' not found", ex);
        }
    }

    private TokenUserDetails getUserDetails(User user) {
        return new TokenUserDetails(
                user.getEmail(),
                user.getPassword(),
                tokenService.encode(user),
                user.isEnabled(),
                getAuthorities(user.getRoles()));
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }
}
