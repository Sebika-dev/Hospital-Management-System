package com.example.Hospital.Management.System.controller;
import com.example.Hospital.Management.System.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalApiController {
    private final HospitalService service;
    @Autowired public HospitalApiController(HospitalService service) { this.service = service; }
    @GetMapping("/{id}/resources")
    public ResponseEntity<Map<String, Object>> getResources(@PathVariable Long id) {
        return service.getHospitalById(id).map(h -> {
            Map<String, Object> r = new HashMap<>();
            r.put("departments", h.getDepartments().stream().map(d -> Map.of("id", d.getId(), "name", d.getName())).collect(Collectors.toList()));
            r.put("rooms", h.getRooms().stream().map(rm -> Map.of("id", rm.getId(), "number", rm.getNumber(), "capacity", rm.getCapacity())).collect(Collectors.toList()));
            return ResponseEntity.ok(r);
        }).orElse(ResponseEntity.notFound().build());
    }
}