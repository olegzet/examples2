package com.olegzet.spring.ciklum;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CalculatingController {
    private static final String template = "Number is %s.";

    //@Digits(integer = 2, fraction = 0, message = "Max number should be 99")
    @GetMapping(path = "/calculating")
    public String calculating(@RequestParam(value = "num", required = false) Short num) {
        return String.format(template, num);
    }
}
