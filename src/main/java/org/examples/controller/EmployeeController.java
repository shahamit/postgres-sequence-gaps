package org.examples.controller;

import org.examples.model.Employee;
import org.examples.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(path = "/create-employee")
    public ResponseEntity<Long> simulateErrorOnSave(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.simulateErrorOnSaveEmployee(employee));
    }


    @PostMapping
    public ResponseEntity<Long> persistEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}
