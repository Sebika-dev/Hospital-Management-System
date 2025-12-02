package com.example.Hospital.Management.System.service;
import com.example.Hospital.Management.System.model.Appointment;
import com.example.Hospital.Management.System.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository repo;
    public AppointmentService(AppointmentRepository repo) { this.repo = repo; }
    public List<Appointment> getAllAppointments() { return repo.findAll(); }
    public Optional<Appointment> getAppointmentById(Long id) { return repo.findById(id); }
    public void saveAppointment(Appointment a) { repo.save(a); }
    public void deleteAppointment(Long id) { repo.deleteById(id); }
}