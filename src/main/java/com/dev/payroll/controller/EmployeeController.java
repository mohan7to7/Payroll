package com.dev.payroll.controller;

import com.dev.payroll.dto.EmployeeDto;
import com.dev.payroll.entity.Employee;
import com.dev.payroll.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> all() {
        return employeeService.findAll();
    }

    @PostMapping
    public ResponseEntity<Employee> newEmployee(@Valid @RequestBody EmployeeDto newEmployee) {
        Employee savedEmployee = employeeService.save(newEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @GetMapping("/{id}")
    public Employee one(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PutMapping("/{id}")
    public Employee replaceEmployee(@Valid @RequestBody EmployeeDto newEmployee, @PathVariable Long id) {
        return employeeService.update(id, newEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
