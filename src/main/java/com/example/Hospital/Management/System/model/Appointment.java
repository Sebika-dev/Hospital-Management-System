package com.example.Hospital.Management.System.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Appointment {
    private String id;
    private String departmentId;
    private String patientId;

    // NOI
    private String doctorId;
    private String nurseId;

    private LocalDate admissionDate;
    private AppointmentStatus status;

    // Poți păstra pentru extensii viitoare
    private List<String> medicalStaffIds;

    public Appointment() {
        this.medicalStaffIds = new ArrayList<>();
    }

    public Appointment(String id, String departmentId, String patientId, LocalDate admissionDate, AppointmentStatus status) {
        this.id = id;
        this.departmentId = departmentId;
        this.patientId = patientId;
        this.admissionDate = admissionDate;
        this.status = status;
        this.medicalStaffIds = new ArrayList<>();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public String getNurseId() { return nurseId; }
    public void setNurseId(String nurseId) { this.nurseId = nurseId; }

    public LocalDate getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(LocalDate admissionDate) { this.admissionDate = admissionDate; }

    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }

    public List<String> getMedicalStaffIds() { return medicalStaffIds; }
    public void setMedicalStaffIds(List<String> medicalStaffIds) { this.medicalStaffIds = medicalStaffIds; }
    public void addMedicalStaff(String medicalStaffId) { this.medicalStaffIds.add(medicalStaffId); }
}
