package de.deelthor.tokenexample.resourceserver.partner;

import de.deelthor.tokenexample.resourceserver.security.AccessPartnerRefNr;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang.RandomStringUtils.randomNumeric;

@RestController
public class PartnerController {

    @GetMapping(value = "/partner")
    @AccessPartnerRefNr
    public Partner getByPartnerRefNr(@RequestParam String partnerRefNr) {
        return new Partner(Long.parseLong(randomNumeric(2)), randomAlphabetic(4));
    }
}
