package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Doctor;
import com.example.Hospital.Management.System.repository.DoctorRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository repo;

    public DoctorService(DoctorRepository repo) { this.repo = repo; }

    public List<Doctor> getAllDoctors(String name, Long departmentId, String sortField, String sortDir) {
        Specification<Doctor> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (departmentId != null) {
                predicates.add(cb.equal(root.get("department").get("id"), departmentId));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        return repo.findAll(spec, sort);
    }

    public List<Doctor> getAllDoctors() { return repo.findAll(); }
    public Optional<Doctor> getDoctorById(Long id) { return repo.findById(id); }
    public void saveDoctor(Doctor d) { repo.save(d); }
    public void deleteDoctor(Long id) { repo.deleteById(id); }
}