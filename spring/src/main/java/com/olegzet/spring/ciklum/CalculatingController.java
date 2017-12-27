package com.olegzet.spring.ciklum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@Validated
public class CalculatingController {
    private static final String template = "Number is %s.";

    @Autowired
    ServletContext servletContext;

    @GetMapping(path = "/calculating")
    public Map<String, String> calculating(
            @Min(value = 1, message = "Min number should be 1")
            @Max(value = 99, message = "Max number should be 99")
            @RequestParam(value = "num", required = false) Short num) {
        return Collections.singletonMap("uuid", UUID.randomUUID().toString());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(ConstraintViolationException exception) {

        return new ResponseError(ZonedDateTime.now().toEpochSecond(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                        .findFirst().orElse("ConstraintViolationException, but an error message was lost."), servletContext.getContextPath());
    }
}
