package com.dev.payroll.service;

import com.dev.payroll.dto.EmployeeDto;
import com.dev.payroll.entity.Employee;
import com.dev.payroll.repository.EmployeeRepository;
import com.dev.payroll.util.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public List<Employee> findByRole(String role) {
        return employeeRepository.findByRole(role);
    }

    public List<Employee> findByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public double getAverageSalary() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            return 0.0;
        }
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    public BigDecimal getMonthlyGrossSalary(Long id) {
        double annualSalary = employeeRepository.findSalaryByEmployeeId(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return BigDecimal.valueOf(annualSalary)
                .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
    }

    @Transactional
    public Employee save(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setRole(employeeDto.getRole());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSalary(employeeDto.getSalary());
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee update(Long id, EmployeeDto employeeDto) {
        Employee employee = findById(id);
        employee.setName(employeeDto.getName());
        employee.setRole(employeeDto.getRole());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSalary(employeeDto.getSalary());
        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }
}
