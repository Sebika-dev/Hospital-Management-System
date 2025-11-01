package com.example.Hospital_Management.System.repository;

import com.example.Hospital_Management.System.model.MedicalStaff;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MedicalStaffRepository {
    private final Map<String, MedicalStaff> staff = new HashMap<>();

    public MedicalStaff save(MedicalStaff medicalStaff) {
        staff.put(medicalStaff.getId(), medicalStaff);
        return medicalStaff;
    }

    public List<MedicalStaff> findAll() {
        return new ArrayList<>(staff.values());
    }

    public MedicalStaff findById(String id) {
        return staff.get(id);
    }

    public void delete(String id) {
        staff.remove(id);
    }
}