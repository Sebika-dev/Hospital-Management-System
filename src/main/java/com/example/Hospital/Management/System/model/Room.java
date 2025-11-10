package com.example.Hospital.Management.System.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private String hospitalId;
    private double capacity;
    private String number;
    private RoomStatus status;
    private List<String> appointmentIds;

    public Room() {
        this.appointmentIds = new ArrayList<>();
    }

    public Room(String id, String hospitalId, double capacity, String number, RoomStatus status) {
        this.id = id;
        this.hospitalId = hospitalId;
        this.capacity = capacity;
        this.number = number;
        this.status = status;
        this.appointmentIds = new ArrayList<>();
    }

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

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
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
        return "Room{" +
                "id='" + id + '\'' +
                ", hospitalId='" + hospitalId + '\'' +
                ", capacity=" + capacity +
                ", number='" + number + '\'' +
                ", status=" + status +
                ", appointmentIds=" + appointmentIds +
                '}';
    }
}
