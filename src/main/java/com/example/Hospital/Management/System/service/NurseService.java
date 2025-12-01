package com.example.Hospital.Management.System.service;
import com.example.Hospital.Management.System.model.Nurse;
import com.example.Hospital.Management.System.repository.NurseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NurseService {
    private final NurseRepository repo;
    public NurseService(NurseRepository repo) { this.repo = repo; }
    public List<Nurse> getAllNurses() { return repo.findAll(); }
    public Optional<Nurse> getNurseById(Long id) { return repo.findById(id); }
    public void saveNurse(Nurse n) { repo.save(n); }
    public void deleteNurse(Long id) { repo.deleteById(id); }
}