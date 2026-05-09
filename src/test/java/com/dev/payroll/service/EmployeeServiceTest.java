package com.dev.payroll.service;

import com.dev.payroll.dto.EmployeeDto;
import com.dev.payroll.entity.Employee;
import com.dev.payroll.repository.EmployeeRepository;
import com.dev.payroll.util.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        employee = new Employee("John Doe", "Developer");
        employee.setId(1L);
        employeeDto = new EmployeeDto();
        employeeDto.setName("John Doe");
        employeeDto.setRole("Developer");
    }

    @Test
    void findAll_shouldReturnAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(List.of(employee));

        List<Employee> employees = employeeService.findAll();

        assertThat(employees).hasSize(1);
        assertThat(employees.get(0)).isEqualTo(employee);
        verify(employeeRepository).findAll();
    }

    @Test
    void findById_shouldReturnEmployee_whenExists() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee found = employeeService.findById(1L);

        assertThat(found).isEqualTo(employee);
        verify(employeeRepository).findById(1L);
    }

    @Test
    void findById_shouldThrowException_whenNotExists() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.findById(1L))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessage("Could not find employee 1");
    }

    @Test
    void save_shouldReturnSavedEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee saved = employeeService.save(employeeDto);

        assertThat(saved).isEqualTo(employee);
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void update_shouldReturnUpdatedEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee updated = employeeService.update(1L, employeeDto);

        assertThat(updated).isEqualTo(employee);
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(employee);
    }

    @Test
    void deleteById_shouldDelete_whenExists() {
        when(employeeRepository.existsById(1L)).thenReturn(true);

        employeeService.deleteById(1L);

        verify(employeeRepository).deleteById(1L);
    }

    @Test
    void deleteById_shouldThrowException_whenNotExists() {
        when(employeeRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> employeeService.deleteById(1L))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessage("Could not find employee 1");
    }
}
