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
    private final PatientService service;
    @Autowired public PatientApiController(PatientService service) { this.service = service; }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPatient(@PathVariable Long id) {
        return service.getPatientById(id).map(p -> {
            Map<String, Object> dto = new HashMap<>();
            dto.put("id", p.getId());
            dto.put("name", p.getName());
            return ResponseEntity.ok(dto);
        }).orElse(ResponseEntity.notFound().build());
    }
}