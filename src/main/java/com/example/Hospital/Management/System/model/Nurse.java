package com.example.Hospital.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "nurses")
public class Nurse extends MedicalStaff {
    @Enumerated(EnumType.STRING)
    private NurseQualificationLevel qualificationLevel;

    @OneToMany(mappedBy = "nurse")
    private List<Appointment> appointments;

    public Nurse() {}
    public Nurse(String name, NurseQualificationLevel qualificationLevel, Department department) {
        super(name, department);
        this.qualificationLevel = qualificationLevel;
    }
    public NurseQualificationLevel getQualificationLevel() { return qualificationLevel; }
    public void setQualificationLevel(NurseQualificationLevel qualificationLevel) { this.qualificationLevel = qualificationLevel; }
    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
}