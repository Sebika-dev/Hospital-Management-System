package com.example.Hospital.Management.System.model;

import java.util.List;

public class Nurse extends MedicalStaff {
    private String qualificationLevel; // "Registered Nurse", "Practical Nurse"

    // Constructor
    public Nurse() {}

    public Nurse(String id, String name, String departmentId, String qualificationLevel, List<Appointment > appointments) {
        super(id, name, departmentId, appointments);
        this.qualificationLevel = qualificationLevel;
    }

    // Getters and Setters
    public String getQualificationLevel() {
        return qualificationLevel;
    }

    public void setQualificationLevel(String qualificationLevel) {
        this.qualificationLevel = qualificationLevel;
    }
}