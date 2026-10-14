package com.dev.payroll.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "payroll_transaction", uniqueConstraints = {
        @UniqueConstraint(name = "uq_payroll_transaction_employee", columnNames = {"payroll_run_id", "employee_id"})
})
@Getter
@NoArgsConstructor
public class PayrollTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payroll_run_id", nullable = false)
    private PayrollRun payrollRun;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "gross_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal grossAmount;

    public PayrollTransaction(Long employeeId, String employeeName, BigDecimal grossAmount) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.grossAmount = grossAmount;
    }

    void setPayrollRun(PayrollRun payrollRun) {
        this.payrollRun = payrollRun;
    }
}
