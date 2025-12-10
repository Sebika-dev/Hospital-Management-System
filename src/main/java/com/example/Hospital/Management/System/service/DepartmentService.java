package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Department;
import com.example.Hospital.Management.System.repository.DepartmentRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository repo;

    public DepartmentService(DepartmentRepository repo) { this.repo = repo; }

    public List<Department> getAllDepartments(String name, Long hospitalId, String sortField, String sortDir) {
        Specification<Department> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (hospitalId != null) {
                predicates.add(cb.equal(root.get("hospital").get("id"), hospitalId));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        return repo.findAll(spec, sort);
    }

    public List<Department> getAllDepartments() { return repo.findAll(); }
    public Optional<Department> getDepartmentById(Long id) { return repo.findById(id); }
    public void saveDepartment(Department d) { repo.save(d); }
    public void deleteDepartment(Long id) { repo.deleteById(id); }
}