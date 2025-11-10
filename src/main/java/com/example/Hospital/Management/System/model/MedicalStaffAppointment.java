package com.example.Hospital.Management.System.model;

public class MedicalStaffAppointment {
    private String id;
    private String appointmentId;
    private String medicalStaffId;

    public MedicalStaffAppointment() {
    }

    public MedicalStaffAppointment(String id, String appointmentId, String medicalStaffId) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.medicalStaffId = medicalStaffId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(String medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }

    @Override
    public String toString() {
        return "MedicalStaffAppointment{" +
                "id='" + id + '\'' +
                ", appointmentId='" + appointmentId + '\'' +
                ", medicalStaffId='" + medicalStaffId + '\'' +
                '}';
    }
}
