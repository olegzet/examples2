package com.olegzet.spring.calculation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

/**
 * Created by oleg.zorin on 28.12.2017.
 */

@AllArgsConstructor
@Getter
public class ResultsResponse extends RepresentationModel<ResultsResponse> {
    private final List<ResultResponse> results;
}


