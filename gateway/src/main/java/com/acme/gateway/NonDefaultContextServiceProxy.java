package com.acme.gateway;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("sample-service-non-default-context")
public interface NonDefaultContextServiceProxy {
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    String greeting();
}
