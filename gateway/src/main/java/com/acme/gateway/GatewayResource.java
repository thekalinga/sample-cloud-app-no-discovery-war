package com.acme.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayResource {
    @Autowired
    private DefaultContextServiceProxy defaultContextServiceProxy;

    @Autowired
    private NonDefaultContextServiceProxy nonDefaultContextServiceProxy;

    @RequestMapping(value = "/greeting-default-context")
    public String greetingFromDefaultContext() {
        return defaultContextServiceProxy.greeting();
    }

    @RequestMapping(value = "/greeting-non-default-context")
    public String greetingFromNonDefaultContext() {
        return nonDefaultContextServiceProxy.greeting();
    }
}
