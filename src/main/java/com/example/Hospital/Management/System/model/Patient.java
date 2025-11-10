package com.example.Hospital.Management.System.model;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String id;
    private String name;
    private List<String> appointmentIds;

    public Patient() {
        this.appointmentIds = new ArrayList<>();
    }

    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", appointmentIds=" + appointmentIds +
                '}';
    }
}
