package de.deelthor.tokenexample.resourceserver.security.config;

import de.deelthor.tokenexample.resourceserver.security.PartnerRefNr;
import de.deelthor.tokenexample.resourceserver.security.VertragsId;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Component
public class AccessPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        if ((authentication == null) || (target == null) || !(permission instanceof String)) {
            return false;
        }
        Map<String, Object> details = (Map<String, Object>) ((OAuth2AuthenticationDetails) authentication.getDetails()).getDecodedDetails();
        if (target instanceof PartnerRefNr) {
            String partnerRefNr = (String) details.get("partnerRefNr");
            return ((PartnerRefNr) target).getPartnerRefNr().equalsIgnoreCase(partnerRefNr);
        }

        if (target instanceof VertragsId) {
            String vertragsId = (String) details.get("vertragsId");
            return ((VertragsId) target).getVertragsId().equalsIgnoreCase(vertragsId);
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
