package com.dev.payroll.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PayrollRunRequest {
    @NotNull
    @Min(1)
    @Max(9999)
    private Integer year;

    @NotNull
    @Min(1)
    @Max(12)
    private Integer month;
}
