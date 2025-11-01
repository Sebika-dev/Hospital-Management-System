package com.example.Hospital_Management.System.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Appointment {
    private String id;
    private String departmentId;
    private String patientId;
    private LocalDate admissionDate;
    private String status; // "Active" / "Completed"
    private List<MedicalStaff> medicalStaff = new ArrayList<>();

    // Constructor
    public Appointment() {}

    public Appointment(String id, String departmentId, String patientId, LocalDate admissionDate, String status) {
        this.id = id;
        this.departmentId = departmentId;
        this.patientId = patientId;
        this.admissionDate = admissionDate;
        this.status = status;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MedicalStaff> getMedicalStaff() {
        return medicalStaff;
    }

    public void setMedicalStaff(List<MedicalStaff> medicalStaff) {
        this.medicalStaff = medicalStaff;
    }
}
