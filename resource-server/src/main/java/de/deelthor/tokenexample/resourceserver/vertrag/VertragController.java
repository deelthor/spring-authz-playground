package de.deelthor.tokenexample.resourceserver.vertrag;

import de.deelthor.tokenexample.resourceserver.security.AccessVertrag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VertragController {

    @GetMapping(value = "/vertrag")
    @AccessVertrag
    public Vertrag getByPartnerRefNr(@RequestParam String partnerRefNr, @RequestParam String vertragsId) {
        return new Vertrag(Long.parseLong(vertragsId));
    }
}
