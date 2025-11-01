package com.example.Hospital.Management.System.repository;

import com.example.Hospital.Management.System.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PatientRepository {
    private final Map<String, Patient> patients = new HashMap<>();

    public Patient save(Patient patient) {
        patients.put(patient.getId(), patient);
        return patient;
    }

    public List<Patient> findAll() {
        return new ArrayList<>(patients.values());
    }

    public Patient findById(String id) {
        return patients.get(id);
    }

    public void delete(String id) {
        patients.remove(id);
    }
}
