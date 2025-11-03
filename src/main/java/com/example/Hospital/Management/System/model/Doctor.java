package com.example.Hospital.Management.System.model;


import java.util.List;

public class Doctor extends MedicalStaff {
    private String licenseNumber;
    private String specialization;
    private int yearsOfExperience;

    // Constructor
    public Doctor() {}

    public Doctor(String id, String name, String departmentId, String licenseNumber, List<Appointment > appointments) {
        super(id, name, departmentId, appointments);
        this.licenseNumber = licenseNumber;
    }

    // Getters and Setters
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public int getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(int yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }
}
