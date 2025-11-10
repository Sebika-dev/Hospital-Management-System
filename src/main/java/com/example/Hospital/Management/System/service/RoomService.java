package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Room;
import com.example.Hospital.Management.System.repository.inmemory.InMemoryRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final InMemoryRoomRepository roomRepository;

    @Autowired
    public RoomService(@Qualifier("inMemoryRoomRepository") InMemoryRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
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
        roomRepository.delete(id);
    }

    public void deleteAllRooms() {
        roomRepository.deleteAll();
    }
}
