package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Department;
import com.example.Hospital.Management.System.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department created = departmentService.addDepartment(department);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable String id) {
        return departmentService.getDepartmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hospital/{hospitalId}")
    public ResponseEntity<List<Department>> getDepartmentsByHospitalId(@PathVariable String hospitalId) {
        List<Department> departments = departmentService.getDepartmentsByHospitalId(hospitalId);
        return ResponseEntity.ok(departments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable String id, @RequestBody Department department) {
        department.setId(id);
        Department updated = departmentService.updateDepartment(department);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
