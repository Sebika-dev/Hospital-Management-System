package com.example.Hospital.Management.System.model;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String id;
    private String name;
    private String hospitalId;

    public Department() {}

    public Department(String id, String name, String hospitalId) {
        this.id = id;
        this.name = name;
        this.hospitalId = hospitalId;
    }

    // Getters and Setters
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

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }
}