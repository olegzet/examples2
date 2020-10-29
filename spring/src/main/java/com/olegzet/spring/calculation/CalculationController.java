package com.olegzet.spring.calculation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

@RestController
@Scope(SCOPE_REQUEST)
@Validated
@Tag(name = "Calculation", description = "REST API for Calculations")
public class CalculationController {
    @Autowired
    MathService mathService;

    @Operation(summary = "Init calculation",
            description = "Init calculation of difficult expressions",
            tags = {"Calculation"},
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Init calculation was successful, links to result  in output payload",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CalculationResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Wrong parameters of request",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseError.class))),
                    @ApiResponse(responseCode = "405", description = "Validation exception",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseError.class)))},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Params for calculation", required = true,
                    content = @Content(
                            schema = @Schema(implementation = CalculationBody.class))))
    @PostMapping(path = "/calculations"
            , headers = {CONTENT_TYPE + "=" + APPLICATION_JSON_VALUE, ACCEPT + "=" + APPLICATION_JSON_VALUE})
    public CalculationResponse calculations(@Valid @RequestBody CalculationBody calculationBody) {
        String uuid = UUID.randomUUID().toString();
        Short number = calculationBody.getNumber();
        final CalculationResponse calculationResponse
                = new CalculationResponse(mathService.processNumber(uuid, number));
        calculationResponse
                .add(linkTo(methodOn(CalculationController.class).calculations(calculationBody)).withSelfRel())
                .add(linkTo(methodOn(ResultController.class).getResults()).withRel("result:items"))
                .add(linkTo(methodOn(ResultController.class).getResult(uuid)).withRel("result:item"))
                .add(linkTo(methodOn(ResultController.class).extractResult(uuid)).withRel("result:extract"));
        return calculationResponse;
    }

/*    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(ConstraintViolationException exception) {

        return new ResponseError(ZonedDateTime.now().toInstant().toEpochMilli(), HttpStatus.BAD_REQUEST.value()
                , HttpStatus.BAD_REQUEST.getReasonPhrase(),
                exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                        .findFirst().orElse("ConstraintViolationException, but an error message was lost.")
                , servletContext.getContextPath());
    }*/
}
