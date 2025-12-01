package com.example.Hospital.Management.System.repository.infile;

import com.example.Hospital.Management.System.model.Appointment;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("fileAppointmentRepository")
public class FileAppointmentRepository extends AbstractFileRepository<Appointment> {
    public FileAppointmentRepository() {
        super("data/appointments.json", Appointment.class);
    }
    @Override protected String getEntityId(Appointment entity) { return entity.getId(); }
    @Override protected void setEntityId(Appointment entity, String id) { entity.setId(id); }
    @Override protected String getIdPrefix() { return "APP"; }

    public List<Appointment> findByPatientId(String patientId) {
        return storage.values().stream()
                .filter(a -> patientId.equals(a.getPatientId()))
                .collect(Collectors.toList());
    }
    public List<Appointment> findByDepartmentId(String departmentId) {
        return storage.values().stream()
                .filter(a -> departmentId.equals(a.getDepartmentId()))
                .collect(Collectors.toList());
    }
}