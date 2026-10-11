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
            if (repository.count() > 0) {
                return;
            }

            log.info("Preloading {}", repository.save(new Employee("Alice Johnson", "Payroll Manager", "Finance", 70000.0)));
            log.info("Preloading {}", repository.save(new Employee("Bob Smith", "HR Specialist", "People Ops", 70000.0)));
            log.info("Preloading {}", repository.save(new Employee("Charlie Brown", "Developer", "Engineering", 65000.0)));
        };
    }
}
