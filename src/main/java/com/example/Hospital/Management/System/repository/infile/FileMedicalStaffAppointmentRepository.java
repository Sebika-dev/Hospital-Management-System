package com.example.Hospital.Management.System.repository.infile;

import com.example.Hospital.Management.System.model.MedicalStaffAppointment;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component("fileMedicalStaffAppointmentRepository")
public class FileMedicalStaffAppointmentRepository extends AbstractFileRepository<MedicalStaffAppointment> {
    public FileMedicalStaffAppointmentRepository() {
        super("data/staff_appointments.json", MedicalStaffAppointment.class);
    }
    @Override protected String getEntityId(MedicalStaffAppointment entity) { return entity.getId(); }
    @Override protected void setEntityId(MedicalStaffAppointment entity, String id) { entity.setId(id); }
    @Override protected String getIdPrefix() { return "MSA"; }

    public List<MedicalStaffAppointment> findByAppointmentId(String appointmentId) {
        return storage.values().stream()
                .filter(m -> appointmentId.equals(m.getAppointmentId()))
                .collect(Collectors.toList());
    }
    public List<MedicalStaffAppointment> findByMedicalStaffId(String medicalStaffId) {
        return storage.values().stream()
                .filter(m -> medicalStaffId.equals(m.getMedicalStaffId()))
                .collect(Collectors.toList());
    }
}