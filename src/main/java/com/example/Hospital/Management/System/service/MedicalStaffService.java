package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.MedicalStaff;
import com.example.Hospital.Management.System.repository.MedicalStaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MedicalStaffService {

    private final MedicalStaffRepository repo;

    public MedicalStaffService(MedicalStaffRepository repo) {
        this.repo = repo;
    }

    public List<MedicalStaff> findAll() {
        return repo.findAll();
    }

    public MedicalStaff findById(String id) {
        return repo.findById(id).orElse(null); // Returnează null dacă nu găsește
    }

    public void save(MedicalStaff staff) {
        if (staff.getId() == null || staff.getId().isEmpty()) {
            staff.setId(UUID.randomUUID().toString());
        }
        repo.save(staff);
    }

    public void deleteById(String id) {
        repo.delete(id);
    }
}