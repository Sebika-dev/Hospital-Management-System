package com.example.Hospital.Management.System.repository.inmemory;

import com.example.Hospital.Management.System.model.Doctor;
import com.example.Hospital.Management.System.repository.IdGenerator;
import com.example.Hospital.Management.System.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component("inMemoryDoctorRepository")
public class InMemoryDoctorRepository implements Repository<Doctor> {
    private final Map<String, Doctor> storage = new ConcurrentHashMap<>();

    @Override
    public Doctor save(Doctor doctor) {
        if (doctor.getId() == null || doctor.getId().isEmpty()) {
            doctor.setId(IdGenerator.generateId("DOC"));
        }
        storage.put(doctor.getId(), doctor);
        return doctor;
    }

    @Override
    public Optional<Doctor> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Doctor> findAll() {
        return new ArrayList<>(storage.values());
    }

    public List<Doctor> findByDepartmentId(String departmentId) {
        return storage.values().stream()
                .filter(doctor -> doctor.getDepartmentId().equals(departmentId))
                .collect(Collectors.toList());
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
