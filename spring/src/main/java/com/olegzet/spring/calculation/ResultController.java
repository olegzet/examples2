package com.olegzet.spring.calculation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Tag(name = "Result", description = "REST API for Result of Calculations")
public class ResultController {
    @Autowired
    MathService mathService;

    @GetMapping(path = "/results")
    @Operation(tags = {"Result"})
    public ResultsResponse getResults() {
        return new ResultsResponse(mathService.getResults().entrySet()
                .stream()
                .map(item -> includeHal(new ResultResponse(item.getKey(), item.getValue())))
                .collect(Collectors.toList()));
    }

    @Operation(tags = {"Result"})
    @GetMapping(path = "/results/{id}", headers = ACCEPT + "=" + APPLICATION_JSON_VALUE)
    public ResultResponse getResult(@PathVariable("id") String id) {
        long result = mathService.getResult(id);
        if (result == -1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result Not Found");
        }
        return includeHal(new ResultResponse(id, result));
    }

    @Operation(tags = {"Result"})
    @DeleteMapping(path = "/results/{id}", headers = ACCEPT + "=" + APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> extractResult(@PathVariable("id") String id) {
        long result = mathService.extractResult(id);
        if (result == -1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result Not Found");
        }
        return ResponseEntity.noContent().build();
    }

    private ResultResponse includeHal(ResultResponse resultResponse) {
        String id = resultResponse.getUuid();
        resultResponse.add(linkTo(methodOn(ResultController.class).getResult(id)).withSelfRel())
                .add(linkTo(methodOn(ResultController.class).extractResult(id)).withRel("self:extract"))
                .add(linkTo(methodOn(ResultController.class).getResults()).withRel("result:items"));
        return resultResponse;
    }
}
