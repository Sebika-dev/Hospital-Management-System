package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
public class PatientApiController {

    private final PatientService patientService;

    @Autowired
    public PatientApiController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, String>> getPatient(@PathVariable String id) {
        return patientService.getPatientById(id)
                .map(p -> {
                    Map<String,String> dto = new HashMap<>();
                    dto.put("id", p.getId());
                    dto.put("name", p.getName());
                    dto.put("roomId", p.getRoomId());
                    dto.put("departmentId", p.getDepartmentId());
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
