package com.example.Hospital_Management.System.repository;

import com.example.Hospital_Management.System.model.Appointment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AppointmentRepository {
    private final Map<String, Appointment> appointments = new HashMap<>();

    public Appointment save(Appointment appointment) {
        appointments.put(appointment.getId(), appointment);
        return appointment;
    }

    public List<Appointment> findAll() {
        return new ArrayList<>(appointments.values());
    }

    public Appointment findById(String id) {
        return appointments.get(id);
    }

    public void delete(String id) {
        appointments.remove(id);
    }
}
