package com.acme.service.defaultcontext;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultContextResource {
    @RequestMapping(value = "/greeting")
    public String greeting() {
        return "Hello you!!";
    }
}
