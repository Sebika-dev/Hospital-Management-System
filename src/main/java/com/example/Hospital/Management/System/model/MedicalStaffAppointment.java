package com.example.Hospital.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "medical_staff_appointments")
public class MedicalStaffAppointment extends BaseEntity {
    @NotNull private Long appointmentId;
    @NotNull private Long medicalStaffId;
    private String staffType;

    public MedicalStaffAppointment() {}
    public MedicalStaffAppointment(Long appointmentId, Long medicalStaffId, String staffType) {
        this.appointmentId = appointmentId; this.medicalStaffId = medicalStaffId; this.staffType = staffType;
    }
    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }
    public Long getMedicalStaffId() { return medicalStaffId; }
    public void setMedicalStaffId(Long medicalStaffId) { this.medicalStaffId = medicalStaffId; }
    public String getStaffType() { return staffType; }
    public void setStaffType(String staffType) { this.staffType = staffType; }
}
