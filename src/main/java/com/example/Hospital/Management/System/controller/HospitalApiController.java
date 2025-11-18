package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Department;
import com.example.Hospital.Management.System.model.Room;
import com.example.Hospital.Management.System.service.DepartmentService;
import com.example.Hospital.Management.System.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalApiController {

    private final DepartmentService departmentService;
    private final RoomService roomService;

    @Autowired
    public HospitalApiController(DepartmentService departmentService, RoomService roomService) {
        this.departmentService = departmentService;
        this.roomService = roomService;
    }

    @GetMapping("/{id}/resources")
    public ResponseEntity<Map<String, Object>> getHospitalResources(@PathVariable String id) {
        List<Department> departments = departmentService.getDepartmentsByHospitalId(id);
        List<Room> rooms = roomService.getRoomsByHospitalId(id);

        Map<String, Object> response = new HashMap<>();
        response.put("departments", departments);
        response.put("rooms", rooms);

        return ResponseEntity.ok(response);
    }
}