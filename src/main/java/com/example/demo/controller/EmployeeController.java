package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/employees/all")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> listAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/managers/all")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> listAllManagers() {
        return employeeService.fetchAllManagers();
    }

    @PostMapping(value = "/add/employee", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
}
