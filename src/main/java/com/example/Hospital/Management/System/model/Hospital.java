package com.example.Hospital.Management.System.model;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String id;
    private String name;
    private String city;
    private List<String> departmentIds;
    private List<String> roomIds;

    public Hospital() {
        this.departmentIds = new ArrayList<>();
        this.roomIds = new ArrayList<>();
    }

    public Hospital(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.departmentIds = new ArrayList<>();
        this.roomIds = new ArrayList<>();
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<String> departmentIds) {
        this.departmentIds = departmentIds;
    }

    public void addDepartment(String departmentId) {
        this.departmentIds.add(departmentId);
    }

    public List<String> getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(List<String> roomIds) {
        this.roomIds = roomIds;
    }

    public void addRoom(String roomId) {
        this.roomIds.add(roomId);
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", departmentIds=" + departmentIds +
                ", roomIds=" + roomIds +
                '}';
    }
}
