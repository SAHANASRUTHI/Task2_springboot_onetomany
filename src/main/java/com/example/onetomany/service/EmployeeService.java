package com.example.onetomany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onetomany.entity.Employee;
import com.example.onetomany.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee emp){
       
        return employeeRepository.save(emp);

    }


    public List<Employee> getEmployeeByDeptname(String deptname){
        List<Employee> employees=employeeRepository.findByDepartment_Deptname(deptname);

        if(employees!=null){
            return employees;
        }
        return null;
    }





    
    
}
