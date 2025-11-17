package com.example.Hospital.Management.System.repository.infile;

import com.example.Hospital.Management.System.model.Hospital;
import org.springframework.stereotype.Component;

@Component("fileHospitalRepository")
public class FileHospitalRepository extends AbstractFileRepository<Hospital> {
    public FileHospitalRepository() {
        super("data/hospitals.json", Hospital.class);
    }
    @Override protected String getEntityId(Hospital entity) { return entity.getId(); }
    @Override protected void setEntityId(Hospital entity, String id) { entity.setId(id); }
    @Override protected String getIdPrefix() { return "HOS"; }
}