package com.acme.service.defaultcontext;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class DefaultContextResource {
    @RequestMapping(value = "/greeting")
    public String greeting() {
        return "Hello you!!";
    }

    @RequestMapping(value = "/orderBeer", method = RequestMethod.POST)
    public ResponseEntity<Beer> getMovie(@RequestBody Person person) {
        if (person.isUnderAge()) {
            return new ResponseEntity<Beer>(Beer.builder().name("NoSoEasy").build(), HttpStatus.PRECONDITION_FAILED);
        }

        return new ResponseEntity<Beer>(Beer.builder().name("HappyFlow").build(), HttpStatus.OK);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Person {
        private String name;
        private int age;

        public boolean isUnderAge() {
            return (age < 18);
        }
    }

    @Getter
    @Setter
    @Builder
    public static class Beer {
        private String name;
    }

}
