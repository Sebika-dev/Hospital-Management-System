package com.example.Hospital.Management.System.service;
import com.example.Hospital.Management.System.model.MedicalStaffAppointment;
import com.example.Hospital.Management.System.repository.MedicalStaffAppointmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicalStaffAppointmentService {
    private final MedicalStaffAppointmentRepository repo;
    public MedicalStaffAppointmentService(MedicalStaffAppointmentRepository repo) { this.repo = repo; }
    public List<MedicalStaffAppointment> getAll() { return repo.findAll(); }
    public void save(MedicalStaffAppointment msa) { repo.save(msa); }
    public void delete(Long id) { repo.deleteById(id); }
}