package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Nurse;
import com.example.Hospital.Management.System.model.NurseQualificationLevel;
import com.example.Hospital.Management.System.repository.NurseRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NurseService {
    private final NurseRepository repo;

    public NurseService(NurseRepository repo) { this.repo = repo; }

    public List<Nurse> getAllNurses(String name, Long departmentId, NurseQualificationLevel level, String sortField, String sortDir) {
        Specification<Nurse> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (departmentId != null) {
                predicates.add(cb.equal(root.get("department").get("id"), departmentId));
            }
            if (level != null) {
                predicates.add(cb.equal(root.get("qualificationLevel"), level));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        return repo.findAll(spec, sort);
    }

    public List<Nurse> getAllNurses() { return repo.findAll(); }
    public Optional<Nurse> getNurseById(Long id) { return repo.findById(id); }
    public void saveNurse(Nurse n) { repo.save(n); }
    public void deleteNurse(Long id) { repo.deleteById(id); }
}