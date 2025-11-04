package com.example.Hospital.Management.System.model;

import java.util.ArrayList;
import java.util.List;

public abstract class MedicalStaff {
    private String id;
    private String name;
    private String departmentId;
    private List<String> appointmentIds = new ArrayList<>();

    public MedicalStaff() {}

    public MedicalStaff(String id, String name, String departmentId) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }
    public List<String> getAppointmentIds() { return appointmentIds; }
    public void setAppointmentIds(List<String> appointmentIds) {
        this.appointmentIds = appointmentIds != null ? new ArrayList<>(appointmentIds) : new ArrayList<>();
    }
    public void addAppointmentId(String apptId) {
        if (apptId != null && !this.appointmentIds.contains(apptId)) this.appointmentIds.add(apptId);
    }
    public void removeAppointmentId(String apptId) {
        if (apptId != null) this.appointmentIds.remove(apptId);
    }
}