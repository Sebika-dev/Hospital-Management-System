package com.example.Hospital.Management.System.repository.infile;

import com.example.Hospital.Management.System.model.Doctor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("fileDoctorRepository")
public class FileDoctorRepository extends AbstractFileRepository<Doctor> {
    public FileDoctorRepository() {
        super("data/doctors.json", Doctor.class);
    }
    @Override protected String getEntityId(Doctor entity) { return entity.getId(); }
    @Override protected void setEntityId(Doctor entity, String id) { entity.setId(id); }
    @Override protected String getIdPrefix() { return "DOC"; }

    public List<Doctor> findByDepartmentId(String departmentId) {
        return storage.values().stream()
                .filter(d -> departmentId.equals(d.getDepartmentId()))
                .collect(Collectors.toList());
    }
}