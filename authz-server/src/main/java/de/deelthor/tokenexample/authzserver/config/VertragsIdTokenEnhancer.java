package de.deelthor.tokenexample.authzserver.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class VertragsIdTokenEnhancer implements TokenEnhancer{
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInformation = new HashMap<>(accessToken.getAdditionalInformation());
        additionalInformation.put("vertragsId", "666");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        return accessToken;
    }
}
