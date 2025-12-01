package com.example.Hospital.Management.System.repository.infile;

import com.example.Hospital.Management.System.model.Department;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("fileDepartmentRepository")
public class FileDepartmentRepository extends AbstractFileRepository<Department> {
    public FileDepartmentRepository() {
        super("data/departments.json", Department.class);
    }
    @Override protected String getEntityId(Department entity) { return entity.getId(); }
    @Override protected void setEntityId(Department entity, String id) { entity.setId(id); }
    @Override protected String getIdPrefix() { return "DEPT"; }

    public List<Department> findByHospitalId(String hospitalId) {
        return storage.values().stream()
                .filter(dept -> hospitalId.equals(dept.getHospitalId()))
                .collect(Collectors.toList());
    }
}