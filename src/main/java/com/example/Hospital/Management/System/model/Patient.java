package com.example.Hospital.Management.System.model;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String id;
    private String name;
    private String phoneNumber;
    private String email;

    // NOI
    private String roomId;
    private String departmentId;

    private List<String> appointmentIds = new ArrayList<>();

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public List<String> getAppointmentIds() { return appointmentIds; }
    public void setAppointmentIds(List<String> appointmentIds) { this.appointmentIds = appointmentIds; }
    public void addAppointment(String id) { this.appointmentIds.add(id); }
}
