package com.olegzet.spring.ciklum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by oleg.zorin on 27.12.2017.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseError {
    private long timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
