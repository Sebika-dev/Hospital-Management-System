package com.example.Hospital.Management.System.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private String hospitalId;
    private String number;
    private int capacity; // Modificat din double în int
    private RoomStatus status;

    private List<String> appointmentIds = new ArrayList<>();

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getHospitalId() { return hospitalId; }
    public void setHospitalId(String hospitalId) { this.hospitalId = hospitalId; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public int getCapacity() { return capacity; } // Getter actualizat
    public void setCapacity(int capacity) { this.capacity = capacity; } // Setter actualizat

    public RoomStatus getStatus() { return status; }
    public void setStatus(RoomStatus status) { this.status = status; }

    public List<String> getAppointmentIds() { return appointmentIds; }
    public void setAppointmentIds(List<String> appointmentIds) { this.appointmentIds = appointmentIds; }
    public void addAppointment(String id) { this.appointmentIds.add(id); }
}