package com.example.onetomany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.example.onetomany.dto.DepartmentDTO;
import com.example.onetomany.entity.Department;
import com.example.onetomany.service.DepartmentService;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/depart")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        System.out.println("Received department: " + department);

        // option 1 : used to explcity set depatment for the employees if not set it is
        // not referenced and contains null as foreign key.
        // option 2 : same done in Department entity. which sets dept while setting the
        // other emp details
        // if (department.getEmployees() != null) {
        // for (Employee employee : department.getEmployees()) {
        // employee.setDepartment(department);
        // }
        // }
        Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/{empname}")
    public ResponseEntity<DepartmentDTO> getDepartmentByEmployeeName(@PathVariable String empname) {
        Department department = departmentService.getDepartmentNameOfEmployee(empname);
        if (department != null) {
            DepartmentDTO departmentDTO = new DepartmentDTO(department.getDeptid(), department.getDeptname());
            return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<Department>> getDepartments(){
        List<Department> departments= departmentService.getAllDepartments();
        if (departments != null) {
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartmentName(@PathVariable int id,@RequestParam String deptname){
        Department updatedDepartment=departmentService.updateDepartmentName(id, deptname);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }



   
}
