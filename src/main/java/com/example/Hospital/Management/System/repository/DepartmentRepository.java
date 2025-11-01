package com.example.Hospital.Management.System.repository;

import com.example.Hospital.Management.System.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DepartmentRepository {
    private final Map<String, Department> departments = new HashMap<>();

    public Department save(Department department) {
        departments.put(department.getId(), department);
        return department;
    }

    public List<Department> findAll() {
        return new ArrayList<>(departments.values());
    }

    public Department findById(String id) {
        return departments.get(id);
    }

    public void delete(String id) {
        departments.remove(id);
    }
}
