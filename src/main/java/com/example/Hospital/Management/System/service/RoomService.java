package com.example.Hospital.Management.System.service;
import com.example.Hospital.Management.System.model.Room;
import com.example.Hospital.Management.System.repository.RoomRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository repo;
    public RoomService(RoomRepository repo) { this.repo = repo; }
    public List<Room> getAllRooms() { return repo.findAll(); }
    public Optional<Room> getRoomById(Long id) { return repo.findById(id); }
    public void saveRoom(Room r) { repo.save(r); }
    public void deleteRoom(Long id) { repo.deleteById(id); }
}