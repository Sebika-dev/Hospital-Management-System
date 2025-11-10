package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Appointment;
import com.example.Hospital.Management.System.repository.inmemory.InMemoryAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final InMemoryAppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(@Qualifier("inMemoryAppointmentRepository") InMemoryAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Optional<Appointment> getAppointmentById(String id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByPatientId(String patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByDepartmentId(String departmentId) {
        return appointmentRepository.findByDepartmentId(departmentId);
    }

    public void deleteAppointment(String id) {
        appointmentRepository.delete(id);
    }

    public void deleteAllAppointments() {
        appointmentRepository.deleteAll();
    }
}
