package com.example.Hospital_Management.System.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private String hospitalId;
    private double capacity;
    private String number;
    private String status; // "Available" / "Occupied"
    private List<Appointment> appointments = new ArrayList<>();

    private String type; // "ICU", "General", "Emergency"
    private boolean hasMonitoringEquipment;

    // Constructor
    public Room() {}

    public Room(String id, String hospitalId, String number, double capacity, String status) {
        this.id = id;
        this.hospitalId = hospitalId;
        this.number = number;
        this.capacity = capacity;
        this.status = status;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean isHasMonitoringEquipment() { return hasMonitoringEquipment; }
    public void setHasMonitoringEquipment(boolean hasMonitoringEquipment) { this.hasMonitoringEquipment = hasMonitoringEquipment; }
}