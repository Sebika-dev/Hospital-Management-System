package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Doctor;
import com.example.Hospital.Management.System.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor created = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable String id) {
        return doctorService.getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Doctor>> getDoctorsByDepartmentId(@PathVariable String departmentId) {
        List<Doctor> doctors = doctorService.getDoctorsByDepartmentId(departmentId);
        return ResponseEntity.ok(doctors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable String id, @RequestBody Doctor doctor) {
        doctor.setId(id);
        Doctor updated = doctorService.updateDoctor(doctor);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable String id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
