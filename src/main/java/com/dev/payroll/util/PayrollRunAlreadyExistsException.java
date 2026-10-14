package com.dev.payroll.util;

import java.time.YearMonth;

public class PayrollRunAlreadyExistsException extends RuntimeException {
    public PayrollRunAlreadyExistsException(YearMonth period) {
        super("A payroll run already exists for " + period);
    }
}
