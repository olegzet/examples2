package com.olegzet.mservice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LampDTO {
    private final String code;
    private final String ip;
    private final String status;
}
