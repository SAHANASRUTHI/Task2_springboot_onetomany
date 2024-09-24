package com.example.onetomany.repository;

import com.example.onetomany.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findByEmployees_Empname(String empname);
}
