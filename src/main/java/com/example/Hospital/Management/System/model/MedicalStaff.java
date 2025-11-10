package com.example.Hospital.Management.System.model;

import java.util.ArrayList;
import java.util.List;

public abstract class MedicalStaff {
    private String id;
    private String name;
    private List<String> appointmentIds;
    private String departmentId;

    public MedicalStaff() {
        this.appointmentIds = new ArrayList<>();
    }

    public MedicalStaff(String id, String name, String departmentId) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.appointmentIds = new ArrayList<>();
    }

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

    public List<String> getAppointmentIds() {
        return appointmentIds;
    }

    public void setAppointmentIds(List<String> appointmentIds) {
        this.appointmentIds = appointmentIds;
    }

    public void addAppointment(String appointmentId) {
        this.appointmentIds.add(appointmentId);
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
