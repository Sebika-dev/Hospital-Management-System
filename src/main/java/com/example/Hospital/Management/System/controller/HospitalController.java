package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Hospital;
import com.example.Hospital.Management.System.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping
    public ResponseEntity<Hospital> createHospital(@RequestBody Hospital hospital) {
        Hospital created = hospitalService.addHospital(hospital);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        return ResponseEntity.ok(hospitals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable String id) {
        return hospitalService.getHospitalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable String id, @RequestBody Hospital hospital) {
        hospital.setId(id);
        Hospital updated = hospitalService.updateHospital(hospital);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable String id) {
        hospitalService.deleteHospital(id);
        return ResponseEntity.noContent().build();
    }
}
