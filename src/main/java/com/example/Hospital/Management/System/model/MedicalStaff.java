package com.example.Hospital.Management.System.model;

import java.util.ArrayList;
import java.util.List;

public class MedicalStaff {
    private String id;
    private String name;
    private List<Appointment> appointments = new ArrayList<>();
    private String departmentId;
    private String role;

    // Constructor
    public MedicalStaff() {}

    public MedicalStaff(String id, String name, String departmentId, List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.appointments = appointments;
    }

    // Getters and Setters

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}