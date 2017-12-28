package com.olegzet.spring.ciklum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.ZonedDateTime;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

@RestController
@Scope(SCOPE_REQUEST)
@Validated
public class CalculatingController {
    @Autowired
    ServletContext servletContext;

    @Autowired
    MathService mathService;

    @GetMapping(path = "/calculating")
    public ResponseSuccess calculating(
            @Min(value = 1, message = "Min number should be 1")
            @Max(value = 99, message = "Max number should be 99")
            @RequestParam(value = "num", required = false) short num) {
        final ResponseSuccess responseSuccess = new ResponseSuccess(mathService.processNumber(num).toString());
        responseSuccess.add(linkTo(methodOn(CalculatingController.class).calculating(num)).withSelfRel());
        return responseSuccess;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(ConstraintViolationException exception) {

        return new ResponseError(ZonedDateTime.now().toEpochSecond(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                        .findFirst().orElse("ConstraintViolationException, but an error message was lost."), servletContext.getContextPath());
    }
}
