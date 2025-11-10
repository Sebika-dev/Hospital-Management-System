package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Appointment;
import com.example.Hospital.Management.System.model.Patient;
import com.example.Hospital.Management.System.repository.inmemory.InMemoryAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final InMemoryAppointmentRepository appointmentRepository;
    private final PatientService patientService;

    @Autowired
    public AppointmentService(InMemoryAppointmentRepository appointmentRepository,
                              PatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
    }

    public Appointment addAppointment(Appointment appointment) {
        Appointment saved = appointmentRepository.save(appointment);
        if (saved.getPatientId() != null) {
            patientService.getPatientById(saved.getPatientId()).ifPresent(p -> {
                if (!p.getAppointmentIds().contains(saved.getId())) {
                    p.addAppointment(saved.getId());
                    patientService.updatePatient(p);
                }
            });
        }
        return saved;
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
        appointmentRepository.findById(id).ifPresent(app -> {
            if (app.getPatientId() != null) {
                patientService.getPatientById(app.getPatientId()).ifPresent(p -> {
                    p.getAppointmentIds().remove(id);
                    patientService.updatePatient(p);
                });
            }
        });
        appointmentRepository.delete(id);
    }

    public void deleteAllAppointments() {
        appointmentRepository.deleteAll();
    }
}
