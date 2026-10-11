package com.dev.payroll.repository;

import com.dev.payroll.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByRole(String role);

    List<Employee> findByDepartment(String department);
}
