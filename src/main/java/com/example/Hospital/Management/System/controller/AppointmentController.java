package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Appointment;
import com.example.Hospital.Management.System.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment created = appointmentService.addAppointment(appointment);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable String id) {
        return appointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable String patientId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patientId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDepartmentId(@PathVariable String departmentId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDepartmentId(departmentId);
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable String id, @RequestBody Appointment appointment) {
        appointment.setId(id);
        Appointment updated = appointmentService.updateAppointment(appointment);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
