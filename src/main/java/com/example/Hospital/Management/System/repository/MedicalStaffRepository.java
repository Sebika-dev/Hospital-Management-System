package com.example.Hospital.Management.System.repository;

import com.example.Hospital.Management.System.model.MedicalStaff;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MedicalStaffRepository {

    private final Map<String, MedicalStaff> store = new HashMap<>();

    public List<MedicalStaff> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<MedicalStaff> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public void save(MedicalStaff staff) {
        if (staff.getId() == null || staff.getId().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        store.put(staff.getId(), staff);
    }

    public void delete(String id) {
        store.remove(id);
    }
}