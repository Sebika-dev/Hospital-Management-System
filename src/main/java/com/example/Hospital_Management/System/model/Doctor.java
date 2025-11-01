package com.example.Hospital_Management.System.model;


public class Doctor extends MedicalStaff {
    private String licenseNumber;
    private String specialization;
    private int yearsOfExperience;

    // Constructor
    public Doctor() {}

    public Doctor(String id, String name, String departmentId, String licenseNumber) {
        super(id, name, departmentId);
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
