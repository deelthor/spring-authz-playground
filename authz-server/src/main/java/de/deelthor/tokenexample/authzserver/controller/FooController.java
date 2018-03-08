package de.deelthor.tokenexample.authzserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springjwt")
public class FooController {

    @RequestMapping(value ="/cities")
    public String getUser(){
        return "Foo";
    }
}
