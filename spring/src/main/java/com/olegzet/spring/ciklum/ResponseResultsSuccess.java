package com.olegzet.spring.ciklum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.util.Map;

/**
 * Created by oleg.zorin on 28.12.2017.
 */

@AllArgsConstructor
@Getter
public class ResponseResultsSuccess extends ResourceSupport {
    private final Map<String, Long> results;
}


