package de.deelthor.tokenexample.authzserver.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public abstract class RequestParamTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, String> requestParameters = authentication.getOAuth2Request().getRequestParameters();
        String requestParameterKey = getRequestParameterKey();
        if (!requestParameters.containsKey(requestParameterKey)){
            return accessToken;
        }
        String requestParameterValue = requestParameters.get(requestParameterKey);
        //TODO authorisieren
        Map<String, Object> additionalInformation = new HashMap<>(accessToken.getAdditionalInformation());
        additionalInformation.put(requestParameterKey, requestParameterValue);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        return accessToken;
    }

    abstract String getRequestParameterKey();
}
