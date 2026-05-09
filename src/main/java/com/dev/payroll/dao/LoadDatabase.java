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
            log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
            log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
            log.info("Preloading " + repository.save(new Employee("Bilbo Baggins 1", "burglar")));
            log.info("Preloading " + repository.save(new Employee("Frodo Baggins 1", "thief")));
            log.info("Preloading " + repository.save(new Employee("Bilbo Baggins 2", "burglar")));
            log.info("Preloading " + repository.save(new Employee("Frodo Baggins 2", "thief")));
            log.info("Preloading " + repository.save(new Employee("Bilbo Baggins 3", "burglar")));
            log.info("Preloading " + repository.save(new Employee("Frodo Baggins 3", "thief")));
            log.info("Preloading " + repository.save(new Employee("Bilbo Baggins 4", "burglar")));
            log.info("Preloading " + repository.save(new Employee("Frodo Baggins 4", "thief")));
            log.info("Preloading " + repository.save(new Employee("Frodo Baggins 5", "thief")));

        };
    }
}
