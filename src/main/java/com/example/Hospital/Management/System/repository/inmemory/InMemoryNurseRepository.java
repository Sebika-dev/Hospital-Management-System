package com.example.Hospital.Management.System.repository.inmemory;

import com.example.Hospital.Management.System.model.Nurse;
import com.example.Hospital.Management.System.repository.IdGenerator;
import com.example.Hospital.Management.System.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component("inMemoryNurseRepository")
public class InMemoryNurseRepository implements Repository<Nurse> {
    private final Map<String, Nurse> storage = new ConcurrentHashMap<>();

    @Override
    public Nurse save(Nurse nurse) {
        if (nurse.getId() == null || nurse.getId().isEmpty()) {
            nurse.setId(IdGenerator.generateId("NUR"));
        }
        storage.put(nurse.getId(), nurse);
        return nurse;
    }

    @Override
    public Optional<Nurse> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Nurse> findAll() {
        return new ArrayList<>(storage.values());
    }

    public List<Nurse> findByDepartmentId(String departmentId) {
        return storage.values().stream()
                .filter(nurse -> nurse.getDepartmentId().equals(departmentId))
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
