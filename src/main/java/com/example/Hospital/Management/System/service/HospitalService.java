package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Hospital;
import com.example.Hospital.Management.System.repository.HospitalRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    private final HospitalRepository repo;

    public HospitalService(HospitalRepository repo) { this.repo = repo; }

    public List<Hospital> getAllHospitals(String name, String city, String sortField, String sortDir) {
        Specification<Hospital> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (city != null && !city.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        return repo.findAll(spec, sort);
    }

    // Metoda veche (o păstrăm pentru compatibilitate dacă e nevoie, dar ideal o înlocuiești peste tot)
    public List<Hospital> getAllHospitals() { return repo.findAll(); }

    public Optional<Hospital> getHospitalById(Long id) { return repo.findById(id); }
    public void saveHospital(Hospital h) { repo.save(h); }
    public void deleteHospital(Long id) { repo.deleteById(id); }
}