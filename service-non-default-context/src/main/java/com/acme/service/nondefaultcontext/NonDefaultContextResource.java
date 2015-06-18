package com.acme.service.nondefaultcontext;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NonDefaultContextResource {
    @RequestMapping(value = "/greeting")
    public String greeting() {
        return "Hello you from context aware!!";
    }
}
