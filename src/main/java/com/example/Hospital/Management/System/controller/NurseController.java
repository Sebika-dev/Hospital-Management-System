package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Nurse;
import com.example.Hospital.Management.System.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nurses")
public class NurseController {

    private final NurseService nurseService;

    @Autowired
    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @PostMapping
    public ResponseEntity<Nurse> createNurse(@RequestBody Nurse nurse) {
        Nurse created = nurseService.addNurse(nurse);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Nurse>> getAllNurses() {
        List<Nurse> nurses = nurseService.getAllNurses();
        return ResponseEntity.ok(nurses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nurse> getNurseById(@PathVariable String id) {
        return nurseService.getNurseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Nurse>> getNursesByDepartmentId(@PathVariable String departmentId) {
        List<Nurse> nurses = nurseService.getNursesByDepartmentId(departmentId);
        return ResponseEntity.ok(nurses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nurse> updateNurse(@PathVariable String id, @RequestBody Nurse nurse) {
        nurse.setId(id);
        Nurse updated = nurseService.updateNurse(nurse);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNurse(@PathVariable String id) {
        nurseService.deleteNurse(id);
        return ResponseEntity.noContent().build();
    }
}
