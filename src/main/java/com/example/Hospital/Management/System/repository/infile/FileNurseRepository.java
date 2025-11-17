package com.example.Hospital.Management.System.repository.infile;

import com.example.Hospital.Management.System.model.Nurse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("fileNurseRepository")
public class FileNurseRepository extends AbstractFileRepository<Nurse> {
    public FileNurseRepository() {
        super("data/nurses.json", Nurse.class);
    }
    @Override protected String getEntityId(Nurse entity) { return entity.getId(); }
    @Override protected void setEntityId(Nurse entity, String id) { entity.setId(id); }
    @Override protected String getIdPrefix() { return "NUR"; }

    public List<Nurse> findByDepartmentId(String departmentId) {
        return storage.values().stream()
                .filter(n -> departmentId.equals(n.getDepartmentId()))
                .collect(Collectors.toList());
    }
}