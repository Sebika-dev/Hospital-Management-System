package com.example.Hospital.Management.System.service;


import com.example.Hospital.Management.System.model.Room;
import com.example.Hospital.Management.System.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(String id) {
        return roomRepository.findById(id);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoomById(String id) {
        roomRepository.delete(id);
    }

    public boolean isRoomAvailable(String id) {
        Room room = getRoomById(id);
        return room != null && "Available".equals(room.getStatus());
    }
}
