package com.example.Hospital.Management.System.service;
import com.example.Hospital.Management.System.model.Department;
import com.example.Hospital.Management.System.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository repo;
    public DepartmentService(DepartmentRepository repo) { this.repo = repo; }
    public List<Department> getAllDepartments() { return repo.findAll(); }
    public Optional<Department> getDepartmentById(Long id) { return repo.findById(id); }
    public void saveDepartment(Department d) { repo.save(d); }
    public void deleteDepartment(Long id) { repo.deleteById(id); }
}