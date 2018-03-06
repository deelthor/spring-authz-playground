package de.deelthor.tokenexample.resourceserver.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private TokenProperties properties;

    public TokenAuthenticationFilter(TokenProperties properties) {
        this.properties = properties;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        logger.debug("Retrieving principal from token");
        return request.getHeader(properties.getHeader());
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return request.getHeader(properties.getHeader());
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
