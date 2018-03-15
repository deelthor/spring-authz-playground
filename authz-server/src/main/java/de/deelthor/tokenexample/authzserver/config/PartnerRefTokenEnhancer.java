package de.deelthor.tokenexample.authzserver.config;

public class PartnerRefTokenEnhancer extends RequestParamTokenEnhancer {

    private static final String PARTNER_REF_NR = "partnerRefNr";

    @Override
    String getRequestParameterKey() {
        return PARTNER_REF_NR;
    }
}
