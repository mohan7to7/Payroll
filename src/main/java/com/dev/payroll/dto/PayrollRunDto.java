package com.dev.payroll.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PayrollRunDto(
        Long id,
        String period,
        LocalDateTime createdAt,
        List<PayrollTransactionDto> transactions
) {
}
