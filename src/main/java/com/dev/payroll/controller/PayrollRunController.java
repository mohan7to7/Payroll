package com.dev.payroll.controller;

import com.dev.payroll.dto.PayrollRunDto;
import com.dev.payroll.dto.PayrollRunRequest;
import com.dev.payroll.service.PayrollRunService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/payroll/runs")
@RequiredArgsConstructor
public class PayrollRunController {
    private final PayrollRunService payrollRunService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PayrollRunDto createRun(@Valid @RequestBody PayrollRunRequest request) {
        return payrollRunService.createRun(YearMonth.of(request.getYear(), request.getMonth()));
    }

    @GetMapping
    public List<PayrollRunDto> allRuns() {
        return payrollRunService.findAllRuns();
    }

    @GetMapping("/{id}")
    public PayrollRunDto oneRun(@PathVariable Long id) {
        return payrollRunService.findRun(id);
    }
}
