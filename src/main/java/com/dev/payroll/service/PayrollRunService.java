package com.dev.payroll.service;

import com.dev.payroll.dto.PayrollRunDto;
import com.dev.payroll.dto.PayrollTransactionDto;
import com.dev.payroll.entity.Employee;
import com.dev.payroll.entity.PayrollRun;
import com.dev.payroll.entity.PayrollTransaction;
import com.dev.payroll.repository.EmployeeRepository;
import com.dev.payroll.repository.PayrollRunRepository;
import com.dev.payroll.util.PayrollRunAlreadyExistsException;
import com.dev.payroll.util.PayrollRunNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PayrollRunService {
    private final EmployeeRepository employeeRepository;
    private final PayrollRunRepository payrollRunRepository;

    @Transactional
    public PayrollRunDto createRun(YearMonth period) {
        LocalDate periodStart = period.atDay(1);
        if (payrollRunRepository.existsByPeriodStart(periodStart)) {
            throw new PayrollRunAlreadyExistsException(period);
        }

        List<Employee> employees = employeeRepository.findAll();
        PayrollRun run = new PayrollRun(periodStart, LocalDateTime.now());
        for (Employee employee : employees) {
            BigDecimal monthlyGross = BigDecimal.valueOf(employee.getSalary())
                    .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
            run.addTransaction(new PayrollTransaction(employee.getId(), employee.getName(), monthlyGross));
        }

        return toDto(payrollRunRepository.save(run));
    }

    public List<PayrollRunDto> findAllRuns() {
        return payrollRunRepository.findAllByOrderByPeriodStartDesc()
                .stream()
                .map(PayrollRunService::toDto)
                .toList();
    }

    public PayrollRunDto findRun(Long id) {
        return payrollRunRepository.findById(id)
                .map(PayrollRunService::toDto)
                .orElseThrow(() -> new PayrollRunNotFoundException(id));
    }

    private static PayrollRunDto toDto(PayrollRun run) {
        List<PayrollTransactionDto> transactions = run.getTransactions().stream()
                .map(transaction -> new PayrollTransactionDto(
                        transaction.getId(),
                        transaction.getEmployeeId(),
                        transaction.getEmployeeName(),
                        transaction.getGrossAmount()))
                .toList();

        return new PayrollRunDto(
                run.getId(),
                YearMonth.from(run.getPeriodStart()).toString(),
                run.getCreatedAt(),
                transactions);
    }
}
