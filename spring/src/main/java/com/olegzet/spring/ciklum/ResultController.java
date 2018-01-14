package com.olegzet.spring.ciklum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

@RestController
public class ResultController {
    @Autowired
    MathService mathService;

    @GetMapping(path = "/results/{id}")
    public ResponseEntity<Map<String, Long>> getResult(@PathVariable("id") String id) {
        long result = mathService.getResult(id);
        if (result == -1) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(Collections.singletonMap("result", result), HttpStatus.OK);
    }

    @PutMapping(path = "/results/{id}/extract")
    public ResponseEntity<Map<String, Long>> extractResult(@PathVariable("id") String id) {
        long result = mathService.extractResult(id);
        if (result == -1) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(Collections.singletonMap("result", result), HttpStatus.OK);
    }

    @GetMapping(path = "/results")
    public ResponseResultsSuccess getResults() {
        return new ResponseResultsSuccess(mathService.getResults());
    }

    private void generateHal(Map<String, Long> results) {
        new ResponseResultsSuccess(results);
        new WeakHashMap();
        /*responseCalculatingSuccess.add(linkTo(methodOn(CalculatingController.class).calculating(num)).withSelfRel());
        responseCalculatingSuccess.add(linkTo(methodOn(ResultController.class).getResults()).withRel("result:items"));
        responseCalculatingSuccess.add(linkTo(methodOn(ResultController.class).getResult(uuid)).withRel("result:item"));
        responseCalculatingSuccess.add(linkTo(methodOn(ResultController.class).extractResult(uuid)).withRel("result:extract"));*/
    }
}
