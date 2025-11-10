package com.example.Hospital.Management.System.repository.inmemory;

import com.example.Hospital.Management.System.model.Room;
import com.example.Hospital.Management.System.repository.IdGenerator;
import com.example.Hospital.Management.System.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component("inMemoryRoomRepository")
public class InMemoryRoomRepository implements Repository<Room> {
    private final Map<String, Room> storage = new ConcurrentHashMap<>();

    @Override
    public Room save(Room room) {
        if (room.getId() == null || room.getId().isEmpty()) {
            room.setId(IdGenerator.generateId("ROOM"));
        }
        storage.put(room.getId(), room);
        return room;
    }

    @Override
    public Optional<Room> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Room> findAll() {
        return new ArrayList<>(storage.values());
    }

    public List<Room> findByHospitalId(String hospitalId) {
        return storage.values().stream()
                .filter(room -> room.getHospitalId().equals(hospitalId))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }
}
