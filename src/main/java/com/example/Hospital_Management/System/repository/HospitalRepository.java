package com.example.Hospital_Management.System.repository;

import com.example.Hospital_Management.System.model.Hospital;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HospitalRepository {
    private final Map<String, Hospital> hospitals = new HashMap<>();

    public Hospital save(Hospital hospital) {
        hospitals.put(hospital.getId(), hospital);
        return hospital;
    }

    public List<Hospital> findAll() {
        return new ArrayList<>(hospitals.values());
    }

    public Hospital findById(String id) {
        return hospitals.get(id);
    }

    public void delete(String id) {
        hospitals.remove(id);
    }
}