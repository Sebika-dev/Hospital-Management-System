package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Patient;
import com.example.Hospital.Management.System.repository.PatientRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository repo;

    public PatientService(PatientRepository repo) { this.repo = repo; }

    public List<Patient> getAllPatients(String name, Long hospitalId, Long departmentId, String email, String sortField, String sortDir) {
        Specification<Patient> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (email != null && !email.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
            }
            if (hospitalId != null) {
                predicates.add(cb.equal(root.get("hospital").get("id"), hospitalId));
            }
            if (departmentId != null) {
                predicates.add(cb.equal(root.get("department").get("id"), departmentId));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        return repo.findAll(spec, sort);
    }

    public List<Patient> getAllPatients() { return repo.findAll(); }
    public Optional<Patient> getPatientById(Long id) { return repo.findById(id); }
    public void savePatient(Patient p) { repo.save(p); }
    public void deletePatient(Long id) { repo.deleteById(id); }
}