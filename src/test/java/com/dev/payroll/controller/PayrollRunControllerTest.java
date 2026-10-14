package com.dev.payroll.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PayrollRunControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createRun_shouldPersistMonthlyPaymentsAndRejectDuplicatePeriod() throws Exception {
        String request = objectMapper.writeValueAsString(
                java.util.Map.of("year", 2098, "month", 1));

        String response = mockMvc.perform(post("/payroll/runs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.period").value("2098-01"))
                .andExpect(jsonPath("$.transactions").isArray())
                .andExpect(jsonPath("$.transactions.length()").value(3))
                .andReturn()
                .getResponse()
                .getContentAsString();

        var run = objectMapper.readTree(response);
        assertThat(run.get("transactions"))
                .anySatisfy(transaction ->
                        assertThat(transaction.get("grossAmount").decimalValue())
                                .isEqualByComparingTo("5416.67"));

        long id = run.get("id").asLong();
        mockMvc.perform(get("/payroll/runs/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactions[0].grossAmount").exists());

        mockMvc.perform(post("/payroll/runs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isConflict());
    }

    @Test
    void createRun_shouldRejectInvalidMonth() throws Exception {
        mockMvc.perform(post("/payroll/runs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"year\":2098,\"month\":13}"))
                .andExpect(status().isBadRequest());
    }
}
