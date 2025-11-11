package com.example.Hospital.Management.System.repository.inmemory;

import com.example.Hospital.Management.System.model.Hospital;
import com.example.Hospital.Management.System.repository.IdGenerator;
import com.example.Hospital.Management.System.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component("inMemoryHospitalRepository")
public class InMemoryHospitalRepository implements Repository<Hospital> {
    private final Map<String, Hospital> storage = new ConcurrentHashMap<>();

    @Override
    public Hospital save(Hospital hospital) {
        if (hospital.getId() == null || hospital.getId().isEmpty()) {
            hospital.setId(IdGenerator.generateId("HOS"));
        }
        storage.put(hospital.getId(), hospital);
        return hospital;
    }

    @Override
    public Optional<Hospital> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Hospital> findAll() {
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
