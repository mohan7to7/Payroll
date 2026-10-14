package com.dev.payroll.dto;

import java.math.BigDecimal;

public record PayrollTransactionDto(Long id, Long employeeId, String employeeName, BigDecimal grossAmount) {
}
