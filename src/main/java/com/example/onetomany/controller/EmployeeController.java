package com.example.onetomany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onetomany.entity.Employee;
import com.example.onetomany.service.EmployeeService;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee newemployee=employeeService.createEmployee(employee);
        return new ResponseEntity<>(newemployee,HttpStatus.OK);
    }


    @GetMapping("/department/{deptname}")
    public ResponseEntity<List<Employee>> getEmployees(@PathVariable String deptname){
        List<Employee> employees=employeeService.getEmployeeByDeptname(deptname);

        if(employees!=null){
            return new ResponseEntity<>(employees,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
}
