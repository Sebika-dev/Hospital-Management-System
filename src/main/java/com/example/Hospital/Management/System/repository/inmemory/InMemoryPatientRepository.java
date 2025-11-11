package com.example.Hospital.Management.System.repository.inmemory;

import com.example.Hospital.Management.System.model.Patient;
import com.example.Hospital.Management.System.repository.IdGenerator;
import com.example.Hospital.Management.System.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component("inMemoryPatientRepository")
public class InMemoryPatientRepository implements Repository<Patient> {
    private final Map<String, Patient> storage = new ConcurrentHashMap<>();

    @Override
    public Patient save(Patient patient) {
        if (patient.getId() == null || patient.getId().isEmpty()) {
            patient.setId(IdGenerator.generateId("PAT"));
        }
        storage.put(patient.getId(), patient);
        return patient;
    }

    @Override
    public Optional<Patient> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Patient> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }
}
