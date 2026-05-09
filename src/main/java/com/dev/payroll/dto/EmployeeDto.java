package com.dev.payroll.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmployeeDto {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Role is mandatory")
    private String role;

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
