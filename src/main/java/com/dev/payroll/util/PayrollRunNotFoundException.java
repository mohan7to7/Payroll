package com.dev.payroll.util;

public class PayrollRunNotFoundException extends RuntimeException {
    public PayrollRunNotFoundException(Long id) {
        super("Could not find payroll run " + id);
    }
}
