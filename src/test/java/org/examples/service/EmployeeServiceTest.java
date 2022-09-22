package org.examples.service;

import org.examples.model.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("integration")
public class EmployeeServiceTest {

    public static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:14.4-alpine")
            .withUsername("admin")
            .withPassword("admin")
            .withDatabaseName("POSTGRESQL")
            .withReuse(false);

    @BeforeAll
    public static void initReusableContainer() {
        postgreSQLContainer.start();
    }

    @Autowired
    private EmployeeService employeeService;


    @Test
    public void verifySequenceGaps() {
        Employee e1 = new Employee();
        e1.setName("John");
        assertEquals(employeeService.saveEmployee(e1), 1);

        Employee e2 = new Employee();
        e2.setName("Sam");

        try {
            employeeService.simulateErrorOnSaveEmployee(e2);
        } catch (Exception e) {
            //ignore known error
        }

        Employee e3 = new Employee();
        e3.setName("John");
        assertEquals(employeeService.saveEmployee(e3), 3);

    }
}
