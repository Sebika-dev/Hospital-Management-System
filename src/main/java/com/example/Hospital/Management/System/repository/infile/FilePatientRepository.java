package com.example.Hospital.Management.System.repository.infile;

import com.example.Hospital.Management.System.model.Patient;
import org.springframework.stereotype.Component;

@Component("filePatientRepository")
public class FilePatientRepository extends AbstractFileRepository<Patient> {
    public FilePatientRepository() {
        super("data/patients.json", Patient.class);
    }
    @Override protected String getEntityId(Patient entity) { return entity.getId(); }
    @Override protected void setEntityId(Patient entity, String id) { entity.setId(id); }
    @Override protected String getIdPrefix() { return "PAT"; }
}