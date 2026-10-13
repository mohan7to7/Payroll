package com.dev.payroll.controller;

import com.dev.payroll.dto.EmployeeDto;
import com.dev.payroll.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    void all_shouldReturnEmployees() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
    void newEmployee_shouldCreateEmployee() throws Exception {
        EmployeeDto dto = new EmployeeDto();
        dto.setName("Jane Doe");
        dto.setRole("Manager");
        dto.setDepartment("Operations");
        dto.setSalary(65000.0);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.role").value("Manager"))
                .andExpect(jsonPath("$.department").value("Operations"));
    }

    @Test
    @Order(3)
    void one_shouldReturnEmployee() throws Exception {
        // Assuming employee with id 1 exists from LoadDatabase
        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @Order(4)
    void one_shouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/employees/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    void replaceEmployee_shouldUpdateEmployee() throws Exception {
        EmployeeDto dto = new EmployeeDto();
        dto.setName("Updated Name");
        dto.setRole("Updated Role");
        dto.setDepartment("Engineering");
        dto.setSalary(88000.0);

        mockMvc.perform(put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.department").value("Engineering"));
    }

    @Test
    @Order(6)
    void getEmployeesByRole_shouldReturnEmployees() throws Exception {
        mockMvc.perform(get("/employees/role/Developer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].role").value("Developer"));
    }

    @Test
    @Order(7)
    void getAverageSalary_shouldReturnAverage() throws Exception {
        mockMvc.perform(get("/employees/stats/average-salary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.averageSalary").value(72000.0));
    }

    @Test
    @Order(8)
    void getMonthlyGrossSalary_shouldReturnCalculatedPayroll() throws Exception {
        mockMvc.perform(get("/employees/1/payroll/monthly"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.monthlyGrossSalary").value(7333.33));
    }

    @Test
    @Order(9)
    void deleteEmployee_shouldDelete() throws Exception {
        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isNoContent());
    }
}
