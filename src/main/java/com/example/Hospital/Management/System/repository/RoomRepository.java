package com.example.Hospital.Management.System.repository;

import com.example.Hospital.Management.System.model.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoomRepository {
    private final Map<String, Room> rooms = new HashMap<>();

    public Room save(Room room) {
        rooms.put(room.getId(), room);
        return room;
    }

    public List<Room> findAll() {
        return new ArrayList<>(rooms.values());
    }

    public Room findById(String id) {
        return rooms.get(id);
    }

    public void delete(String id) {
        rooms.remove(id);
    }
}
