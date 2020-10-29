package com.olegzet.spring.calculation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by oleg.zorin on 27.12.2017.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    ServletContext servletContext;

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(MethodArgumentNotValidException exception) {
        return makeErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()
                , exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
                        .findFirst().orElse("MethodArgumentNotValidException, but an error message was lost."));
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(ConstraintViolationException exception) {
        return makeErrorResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()
                , exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                        .findFirst().orElse("ConstraintViolationException, but an error message was lost."));
    }

    private ResponseError makeErrorResponse(int statusCode, String reasonPhrase, String message) {
        return new ResponseError(ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME), statusCode
                , reasonPhrase, message, servletContext.getContextPath());
    }
}
