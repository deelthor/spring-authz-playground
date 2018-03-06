package de.deelthor.tokenexample.resourceserver.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String getUsername() {
        if (SecurityContextHolder.getContext() != null &&
                SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof TokenUserDetails) {
            return ((TokenUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getProfileName();
        } else {
            return null;
        }
    }
}
