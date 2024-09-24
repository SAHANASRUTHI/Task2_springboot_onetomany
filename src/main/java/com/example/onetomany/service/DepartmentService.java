package com.example.onetomany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onetomany.entity.Department;
import com.example.onetomany.repository.DepartmentRepository;


@Service
public class DepartmentService {

    private  DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartmentNameOfEmployee(String empname){
        return departmentRepository.findByEmployees_Empname(empname);
    }

    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    public Department updateDepartmentName(int id, String newDeptName) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id " + id));

        department.setDeptname(newDeptName);

        return departmentRepository.save(department);
    }

  
    
}
