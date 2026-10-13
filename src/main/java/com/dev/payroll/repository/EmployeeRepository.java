package com.dev.payroll.repository;

import com.dev.payroll.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByRole(String role);

    List<Employee> findByDepartment(String department);

    @Query("select e.salary from Employee e where e.id = :id")
    Optional<Double> findSalaryByEmployeeId(@Param("id") Long id);
}
