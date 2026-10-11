package com.dev.payroll.dao;

import com.dev.payroll.entity.Employee;
import com.dev.payroll.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Preloading {}", repository.save(new Employee("Alice Johnson", "Payroll Manager")));
            log.info("Preloading {}", repository.save(new Employee("Bob Smith", "HR Specialist")));
            log.info("Preloading {}", repository.save(new Employee("Charlie Brown", "Developer")));
        };
    }
}
