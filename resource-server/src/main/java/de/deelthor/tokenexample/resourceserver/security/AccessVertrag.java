package de.deelthor.tokenexample.resourceserver.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
@PreAuthorize("hasPermission(new de.deelthor.tokenexample.resourceserver.security.VertragsId(#vertragsId), 'access') " +
        "and hasPermission(new de.deelthor.tokenexample.resourceserver.security.PartnerRefNr(#partnerRefNr), 'access')")
public @interface AccessVertrag {
}
