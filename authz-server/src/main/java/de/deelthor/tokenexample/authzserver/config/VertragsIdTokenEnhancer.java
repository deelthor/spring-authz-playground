package de.deelthor.tokenexample.authzserver.config;

public class VertragsIdTokenEnhancer extends RequestParamTokenEnhancer {

    private static final String VERTRAGS_ID = "vertragsId";

    @Override
    String getRequestParameterKey() {
        return VERTRAGS_ID;
    }
}
