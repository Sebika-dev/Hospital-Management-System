package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Room;
import com.example.Hospital.Management.System.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room created = roomService.addRoom(room);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable String id) {
        return roomService.getRoomById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/hospital/{hospitalId}")
    public ResponseEntity<List<Room>> getRoomsByHospitalId(@PathVariable String hospitalId) {
        List<Room> rooms = roomService.getRoomsByHospitalId(hospitalId);
        return ResponseEntity.ok(rooms);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable String id, @RequestBody Room room) {
        room.setId(id);
        Room updated = roomService.updateRoom(room);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
