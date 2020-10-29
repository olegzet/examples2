package com.olegzet.mservice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDTO {
    private final String id;
    private final String lampId;
    private final String status;
}
