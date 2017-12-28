package com.olegzet.spring.ciklum;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by oleg.zorin on 28.12.2017.
 */

@Service
public class MathServiceImpl implements MathService {

    @Override
    public UUID processNumber(short num) {
        return UUID.randomUUID();
    }
}
