package com.olegzet.spring.calculation;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CalculationBody {
    @Min(value = 1, message = "Min number should be 1")
    @Max(value = 9999, message = "Max number should be 9999")
    private Short number;
}
