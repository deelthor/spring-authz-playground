package de.deelthor.tokenexample.authzserver.controller;

import de.deelthor.tokenexample.authzserver.TokenUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthzRestController {

    @GetMapping("/token")
    public String getToken(@AuthenticationPrincipal TokenUserDetails principal) {
        return principal.getToken();
    }
}