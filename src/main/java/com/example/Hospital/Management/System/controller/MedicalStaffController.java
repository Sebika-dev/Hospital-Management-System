package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.MedicalStaff;
import com.example.Hospital.Management.System.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-staff")
public class MedicalStaffController {

    private final MedicalStaffService medicalStaffService;

    @Autowired
    public MedicalStaffController(MedicalStaffService medicalStaffService) {
        this.medicalStaffService = medicalStaffService;
    }

    @GetMapping
    public List<MedicalStaff> getAllMedicalStaff() {
        return medicalStaffService.getAllMedicalStaff();
    }

    @GetMapping("/{id}")
    public MedicalStaff getMedicalStaffById(@PathVariable String id) {
        return medicalStaffService.getMedicalStaffById(id);
    }

    @PostMapping
    public MedicalStaff createMedicalStaff(@RequestBody MedicalStaff medicalStaff) {
        return medicalStaffService.saveMedicalStaff(medicalStaff);
    }

    @DeleteMapping("/{id}")
    public void deleteMedicalStaff(@PathVariable String id) {
        medicalStaffService.deleteMedicalStaff(id);
    }
}