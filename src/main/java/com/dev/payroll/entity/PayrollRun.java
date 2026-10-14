package com.dev.payroll.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payroll_run", uniqueConstraints = {
        @UniqueConstraint(name = "uq_payroll_run_period", columnNames = "period_start")
})
@Getter
@NoArgsConstructor
public class PayrollRun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "period_start", nullable = false)
    private LocalDate periodStart;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "payrollRun", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PayrollTransaction> transactions = new ArrayList<>();

    public PayrollRun(LocalDate periodStart, LocalDateTime createdAt) {
        this.periodStart = periodStart;
        this.createdAt = createdAt;
    }

    public void addTransaction(PayrollTransaction transaction) {
        transactions.add(transaction);
        transaction.setPayrollRun(this);
    }
}
