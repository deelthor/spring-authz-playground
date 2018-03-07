package de.deelthor.tokenexample.authzserver;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class TokenUserDetails extends User {

    private String token;

    public TokenUserDetails(String username, String password, String token, boolean enabled, Collection< ? extends GrantedAuthority> authorities) {
        super(username, password, enabled, true, true, true, authorities);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
