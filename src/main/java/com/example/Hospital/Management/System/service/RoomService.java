package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Hospital;
import com.example.Hospital.Management.System.model.Room;
import com.example.Hospital.Management.System.repository.inmemory.InMemoryRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final InMemoryRoomRepository roomRepository;
    private final HospitalService hospitalService;

    @Autowired
    public RoomService(InMemoryRoomRepository roomRepository,
                       @Lazy HospitalService hospitalService) {
        this.roomRepository = roomRepository;
        this.hospitalService = hospitalService;
    }

    public Room addRoom(Room room) {
        Room saved = roomRepository.save(room);
        if (saved.getHospitalId() != null) {
            hospitalService.getHospitalById(saved.getHospitalId()).ifPresent(h -> {
                if (!h.getRoomIds().contains(saved.getId())) {
                    h.addRoom(saved.getId());
                    hospitalService.updateHospital(h);
                }
            });
        }
        return saved;
    }

    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    public Optional<Room> getRoomById(String id) {
        return roomRepository.findById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> getRoomsByHospitalId(String hospitalId) {
        return roomRepository.findByHospitalId(hospitalId);
    }

    public void deleteRoom(String id) {
        roomRepository.findById(id).ifPresent(room -> {
            if (room.getHospitalId() != null) {
                hospitalService.getHospitalById(room.getHospitalId()).ifPresent(h -> {
                    h.getRoomIds().remove(id);
                    hospitalService.updateHospital(h);
                });
            }
        });
        roomRepository.delete(id);
    }

    public void deleteAllRooms() {
        roomRepository.deleteAll();
    }
}
