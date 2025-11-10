package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Department;
import com.example.Hospital.Management.System.repository.Repository;
import com.example.Hospital.Management.System.repository.inmemory.InMemoryDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final InMemoryDepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(@Qualifier("inMemoryDepartmentRepository") InMemoryDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Optional<Department> getDepartmentById(String id) {
        return departmentRepository.findById(id);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<Department> getDepartmentsByHospitalId(String hospitalId) {
        return departmentRepository.findByHospitalId(hospitalId);
    }

    public void deleteDepartment(String id) {
        departmentRepository.delete(id);
    }

    public void deleteAllDepartments() {
        departmentRepository.deleteAll();
    }
}
