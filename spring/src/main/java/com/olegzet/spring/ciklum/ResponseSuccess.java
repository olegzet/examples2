package com.olegzet.spring.ciklum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by oleg.zorin on 28.12.2017.
 */

@AllArgsConstructor
@Getter
public class ResponseSuccess extends ResourceSupport {
    private final String uuid;
}


