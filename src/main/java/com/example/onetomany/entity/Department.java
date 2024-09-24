package com.example.onetomany.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deptid;

    private String deptname;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    Set<Employee> employees;

    // A request with employees is sent as part of the @RequestBody.
    // Spring deserializes the request into the Department object.
    // The setEmployees() method is called with the deserialized Set<Employee>
    // objects.
    // Inside setEmployees(), you loop through the employees and set the department
    // reference for each employee.
    // The Department object is passed to the service layer and saved in the
    // database.

    // Automatically sets the department reference when employees are set
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
        if (employees != null) {
            for (Employee employee : employees) {
                employee.setDepartment(this); // Set department reference for each employee
            }
        }
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setDeptid(int deptid) {
        this.deptid = deptid;
    }

    public int getDeptid() {
        return deptid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

   
    @Override
    public String toString() {
        return "Department [deptid=" + deptid + ", deptname=" + deptname + "]";
        // Avoid printing employees to prevent recursive calls
    }

}
