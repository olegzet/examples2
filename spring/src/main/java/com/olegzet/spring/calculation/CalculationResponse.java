package com.olegzet.spring.calculation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

/**
 * Created by oleg.zorin on 28.12.2017.
 */

@AllArgsConstructor
@Getter
public class CalculationResponse extends RepresentationModel<CalculationResponse> {
    private final String uuid;
}


