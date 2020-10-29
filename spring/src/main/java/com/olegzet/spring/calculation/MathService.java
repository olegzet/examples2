package com.olegzet.spring.calculation;

import java.util.Map;

/**
 * Created by oleg.zorin on 28.12.2017.
 */
public interface MathService {
    String processNumber(String id, short num);

    Map<String, Long> getResults();

    long getResult(String id);

    long extractResult(String id);
}
