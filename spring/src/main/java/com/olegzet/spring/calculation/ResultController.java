package com.olegzet.spring.calculation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Tag(name = "Result", description = "REST API for Result of Calculations")
@RequestMapping("/results")
public class ResultController {
    @Autowired
    MathService mathService;

    @GetMapping(produces = HAL_JSON_VALUE)
    @Operation(tags = {"Result"})
    public ResultsResponse getResults() {
        return new ResultsResponse(mathService.getResults().entrySet()
                .stream()
                .map(item -> includeHal(new ResultResponse(item.getKey(), item.getValue())))
                .collect(Collectors.toList()));
    }

    @Operation(tags = {"Result"})
    @GetMapping(path = "/{id}", produces = HAL_JSON_VALUE)
    public ResultResponse getResult(@PathVariable("id") String id) {
        long result = mathService.getResult(id);
        if (result == -1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result Not Found");
        }
        return includeHal(new ResultResponse(id, result));
    }

    @Operation(tags = {"Result"})
    @DeleteMapping(path = "/{id}", produces = HAL_JSON_VALUE)
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
