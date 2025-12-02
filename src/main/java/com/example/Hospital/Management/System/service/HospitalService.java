package com.example.Hospital.Management.System.service;
import com.example.Hospital.Management.System.model.Hospital;
import com.example.Hospital.Management.System.repository.HospitalRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    private final HospitalRepository repo;
    public HospitalService(HospitalRepository repo) { this.repo = repo; }
    public List<Hospital> getAllHospitals() { return repo.findAll(); }
    public Optional<Hospital> getHospitalById(Long id) { return repo.findById(id); }
    public void saveHospital(Hospital h) { repo.save(h); }
    public void deleteHospital(Long id) { repo.deleteById(id); }
}