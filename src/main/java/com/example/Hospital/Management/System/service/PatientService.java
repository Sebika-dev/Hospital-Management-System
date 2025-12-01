package com.example.Hospital.Management.System.service;
import com.example.Hospital.Management.System.model.Patient;
import com.example.Hospital.Management.System.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository repo;
    public PatientService(PatientRepository repo) { this.repo = repo; }
    public List<Patient> getAllPatients() { return repo.findAll(); }
    public Optional<Patient> getPatientById(Long id) { return repo.findById(id); }
    public void savePatient(Patient p) { repo.save(p); }
    public void deletePatient(Long id) { repo.deleteById(id); }
}