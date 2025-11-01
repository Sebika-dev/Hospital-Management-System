package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.MedicalStaffAppointment;
import com.example.Hospital.Management.System.service.MedicalStaffAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-staff-appointments")
public class MedicalStaffAppointmentController {

    private final MedicalStaffAppointmentService medicalStaffAppointmentService;

    @Autowired
    public MedicalStaffAppointmentController(MedicalStaffAppointmentService medicalStaffAppointmentService) {
        this.medicalStaffAppointmentService = medicalStaffAppointmentService;
    }

    @GetMapping
    public List<MedicalStaffAppointment> getAllMedicalStaffAppointments() {
        return medicalStaffAppointmentService.getAllMedicalStaffAppointments();
    }

    @GetMapping("/{id}")
    public MedicalStaffAppointment getMedicalStaffAppointmentById(@PathVariable String id) {
        return medicalStaffAppointmentService.getMedicalStaffAppointmentById(id);
    }

    @PostMapping
    public MedicalStaffAppointment createMedicalStaffAppointment(@RequestBody MedicalStaffAppointment link) {
        return medicalStaffAppointmentService.saveMedicalStaffAppointment(link);
    }

    @DeleteMapping("/{id}")
    public void deleteMedicalStaffAppointment(@PathVariable String id) {
        medicalStaffAppointmentService.deleteMedicalStaffAppointment(id);
    }
}