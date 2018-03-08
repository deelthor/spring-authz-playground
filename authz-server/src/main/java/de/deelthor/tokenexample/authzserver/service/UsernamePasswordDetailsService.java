package de.deelthor.tokenexample.authzserver.service;

import de.deelthor.tokenexample.authzserver.repository.Role;
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


    public UsernamePasswordDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            return getUserDetails(userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new));
        } catch (UserNotFoundException ex) {
            throw new UsernameNotFoundException("Account for '" + email + "' not found", ex);
        }
    }

    private UserDetails getUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                true, true, true,
                getAuthorities(user.getRoles()));
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }
}
