package com.dev.payroll.repository;

import com.dev.payroll.entity.PayrollRun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PayrollRunRepository extends JpaRepository<PayrollRun, Long> {
    boolean existsByPeriodStart(LocalDate periodStart);

    List<PayrollRun> findAllByOrderByPeriodStartDesc();

    Optional<PayrollRun> findById(Long id);
}
