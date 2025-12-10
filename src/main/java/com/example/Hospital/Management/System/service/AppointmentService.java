package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Appointment;
import com.example.Hospital.Management.System.model.AppointmentStatus;
import com.example.Hospital.Management.System.repository.AppointmentRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository repo;

    public AppointmentService(AppointmentRepository repo) { this.repo = repo; }

    public List<Appointment> getAllAppointments(Long patientId, Long doctorId, AppointmentStatus status,
                                                LocalDate startDate, LocalDate endDate,
                                                String sortField, String sortDir) {
        Specification<Appointment> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (patientId != null) {
                predicates.add(cb.equal(root.get("patient").get("id"), patientId));
            }
            if (doctorId != null) {
                predicates.add(cb.equal(root.get("doctor").get("id"), doctorId));
            }
            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            if (startDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("admissionDate"), startDate));
            }
            if (endDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("admissionDate"), endDate));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        return repo.findAll(spec, sort);
    }

    public List<Appointment> getAllAppointments() { return repo.findAll(); }
    public Optional<Appointment> getAppointmentById(Long id) { return repo.findById(id); }
    public void saveAppointment(Appointment a) { repo.save(a); }
    public void deleteAppointment(Long id) { repo.deleteById(id); }
}