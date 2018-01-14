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
import java.util.UUID;

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
    public ResponseCalculatingSuccess calculating(
            @Min(value = 1, message = "Min number should be 1")
            @Max(value = 9999, message = "Max number should be 9999")
            @RequestParam(value = "num", required = false) short num) {
        String uuid = UUID.randomUUID().toString();
        final ResponseCalculatingSuccess responseCalculatingSuccess = new ResponseCalculatingSuccess(mathService.processNumber(uuid, num));
        responseCalculatingSuccess.add(linkTo(methodOn(CalculatingController.class).calculating(num)).withSelfRel());
        responseCalculatingSuccess.add(linkTo(methodOn(ResultController.class).getResults()).withRel("result:items"));
        responseCalculatingSuccess.add(linkTo(methodOn(ResultController.class).getResult(uuid)).withRel("result:item"));
        responseCalculatingSuccess.add(linkTo(methodOn(ResultController.class).extractResult(uuid)).withRel("result:extract"));
        return responseCalculatingSuccess;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(ConstraintViolationException exception) {

        return new ResponseError(ZonedDateTime.now().toInstant().toEpochMilli(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                        .findFirst().orElse("ConstraintViolationException, but an error message was lost."), servletContext.getContextPath());
    }
}
