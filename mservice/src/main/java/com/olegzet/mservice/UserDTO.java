package com.olegzet.mservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class UserDTO {
    private final String id;
    private final String lampCode;
    private final String privilege;
    private final String status;
}
