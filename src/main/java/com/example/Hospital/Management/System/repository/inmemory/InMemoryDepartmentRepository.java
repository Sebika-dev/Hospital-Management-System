package com.example.Hospital.Management.System.repository.inmemory;

import com.example.Hospital.Management.System.model.Department;
import com.example.Hospital.Management.System.repository.IdGenerator;
import com.example.Hospital.Management.System.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component("inMemoryDepartmentRepository")
public class InMemoryDepartmentRepository implements Repository<Department> {
    private final Map<String, Department> storage = new ConcurrentHashMap<>();

    @Override
    public Department save(Department department) {
        if (department.getId() == null || department.getId().isEmpty()) {
            department.setId(IdGenerator.generateId("DEPT"));
        }
        storage.put(department.getId(), department);
        return department;
    }

    @Override
    public Optional<Department> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Department> findAll() {
        return new ArrayList<>(storage.values());
    }

    public List<Department> findByHospitalId(String hospitalId) {
        return storage.values().stream()
                .filter(dept -> dept.getHospitalId().equals(hospitalId))
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
