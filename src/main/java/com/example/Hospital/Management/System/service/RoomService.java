package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Room;
import com.example.Hospital.Management.System.model.RoomStatus;
import com.example.Hospital.Management.System.repository.RoomRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository repo;

    public RoomService(RoomRepository repo) { this.repo = repo; }

    public List<Room> getAllRooms(String number, Long hospitalId, RoomStatus status, Integer minCapacity, String sortField, String sortDir) {
        Specification<Room> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (number != null && !number.isEmpty()) {
                predicates.add(cb.like(root.get("number"), "%" + number + "%"));
            }
            if (hospitalId != null) {
                predicates.add(cb.equal(root.get("hospital").get("id"), hospitalId));
            }
            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            if (minCapacity != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("capacity"), minCapacity));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        return repo.findAll(spec, sort);
    }

    public List<Room> getAllRooms() { return repo.findAll(); }
    public Optional<Room> getRoomById(Long id) { return repo.findById(id); }
    public void saveRoom(Room r) { repo.save(r); }
    public void deleteRoom(Long id) { repo.deleteById(id); }
}