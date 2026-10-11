package com.dev.payroll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class EmployeeDto {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Role is mandatory")
    private String role;

    @NotBlank(message = "Department is mandatory")
    private String department;

    @PositiveOrZero(message = "Salary must be zero or positive")
    private double salary;
}
