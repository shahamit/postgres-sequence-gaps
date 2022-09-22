package org.examples.service;

import org.examples.events.IdentifiableCreatedEvent;
import org.examples.model.Employee;
import org.examples.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional
    public Long simulateErrorOnSaveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        eventPublisher.publishEvent(new IdentifiableCreatedEvent(savedEmployee));
        throw new IllegalStateException("Simulating rollbacks for finding gaps");
    }

    @Transactional
    public Long saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        eventPublisher.publishEvent(new IdentifiableCreatedEvent(savedEmployee));
        return savedEmployee.getId();
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
