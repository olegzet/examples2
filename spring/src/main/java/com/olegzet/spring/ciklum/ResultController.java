package com.olegzet.spring.ciklum;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ResultController {
    private static final String template = "Result is %s.";

    @GetMapping(path = "/results/{id}")
    public String getResult(@PathVariable("id") UUID id) {
        return String.format(template, id.toString());
    }
}
