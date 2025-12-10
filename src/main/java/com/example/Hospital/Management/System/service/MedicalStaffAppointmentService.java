package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.MedicalStaffAppointment;
import com.example.Hospital.Management.System.repository.MedicalStaffAppointmentRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalStaffAppointmentService {
    private final MedicalStaffAppointmentRepository repo;

    public MedicalStaffAppointmentService(MedicalStaffAppointmentRepository repo) {
        this.repo = repo;
    }

    public List<MedicalStaffAppointment> getAll(Long appointmentId, Long medicalStaffId, String staffType, String sortField, String sortDir) {
        Specification<MedicalStaffAppointment> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtru: ID Programare (Exact)
            if (appointmentId != null) {
                predicates.add(cb.equal(root.get("appointmentId"), appointmentId));
            }

            // Filtru: ID Staff (Exact)
            if (medicalStaffId != null) {
                predicates.add(cb.equal(root.get("medicalStaffId"), medicalStaffId));
            }

            // Filtru: Tip Staff (Exact sau Like, aici Exact e mai logic pentru un enum/string fix)
            if (staffType != null && !staffType.isEmpty()) {
                predicates.add(cb.equal(root.get("staffType"), staffType));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        return repo.findAll(spec, sort);
    }

    // Păstrăm și metoda fără parametri pentru compatibilitate, dacă e cazul
    public List<MedicalStaffAppointment> getAll() { return repo.findAll(); }

    public void save(MedicalStaffAppointment msa) { repo.save(msa); }
    public void delete(Long id) { repo.deleteById(id); }
}