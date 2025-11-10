package com.example.Hospital.Management.System.repository.inmemory;

import com.example.Hospital.Management.System.model.Appointment;
import com.example.Hospital.Management.System.repository.IdGenerator;
import com.example.Hospital.Management.System.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component("inMemoryAppointmentRepository")
public class InMemoryAppointmentRepository implements Repository<Appointment> {
    private final Map<String, Appointment> storage = new ConcurrentHashMap<>();

    @Override
    public Appointment save(Appointment appointment) {
        if (appointment.getId() == null || appointment.getId().isEmpty()) {
            appointment.setId(IdGenerator.generateId("APP"));
        }
        storage.put(appointment.getId(), appointment);
        return appointment;
    }

    @Override
    public Optional<Appointment> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Appointment> findAll() {
        return new ArrayList<>(storage.values());
    }

    public List<Appointment> findByPatientId(String patientId) {
        return storage.values().stream()
                .filter(app -> app.getPatientId().equals(patientId))
                .collect(Collectors.toList());
    }

    public List<Appointment> findByDepartmentId(String departmentId) {
        return storage.values().stream()
                .filter(app -> app.getDepartmentId().equals(departmentId))
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
