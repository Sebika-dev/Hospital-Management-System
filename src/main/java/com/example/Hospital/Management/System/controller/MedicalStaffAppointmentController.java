package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.MedicalStaffAppointment;
import com.example.Hospital.Management.System.service.MedicalStaffAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-staff-appointments")
public class MedicalStaffAppointmentController {

    private final MedicalStaffAppointmentService msaService;

    @Autowired
    public MedicalStaffAppointmentController(MedicalStaffAppointmentService msaService) {
        this.msaService = msaService;
    }

    @PostMapping
    public ResponseEntity<MedicalStaffAppointment> createMedicalStaffAppointment(@RequestBody MedicalStaffAppointment msa) {
        MedicalStaffAppointment created = msaService.addMedicalStaffAppointment(msa);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedicalStaffAppointment>> getAllMedicalStaffAppointments() {
        List<MedicalStaffAppointment> msaList = msaService.getAllMedicalStaffAppointments();
        return ResponseEntity.ok(msaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalStaffAppointment> getMedicalStaffAppointmentById(@PathVariable String id) {
        return msaService.getMedicalStaffAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<MedicalStaffAppointment>> getByAppointmentId(@PathVariable String appointmentId) {
        List<MedicalStaffAppointment> msaList = msaService.getMedicalStaffAppointmentsByAppointmentId(appointmentId);
        return ResponseEntity.ok(msaList);
    }

    @GetMapping("/medical-staff/{medicalStaffId}")
    public ResponseEntity<List<MedicalStaffAppointment>> getByMedicalStaffId(@PathVariable String medicalStaffId) {
        List<MedicalStaffAppointment> msaList = msaService.getMedicalStaffAppointmentsByMedicalStaffId(medicalStaffId);
        return ResponseEntity.ok(msaList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalStaffAppointment(@PathVariable String id) {
        msaService.deleteMedicalStaffAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
