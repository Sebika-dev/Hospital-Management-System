package com.example.Hospital.Management.System.service;
import com.example.Hospital.Management.System.model.Doctor;
import com.example.Hospital.Management.System.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository repo;
    public DoctorService(DoctorRepository repo) { this.repo = repo; }
    public List<Doctor> getAllDoctors() { return repo.findAll(); }
    public Optional<Doctor> getDoctorById(Long id) { return repo.findById(id); }
    public void saveDoctor(Doctor d) { repo.save(d); }
    public void deleteDoctor(Long id) { repo.deleteById(id); }
}