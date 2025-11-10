package com.example.Hospital.Management.System.model;

public class Department {
    private String id;
    private String name;
    private String hospitalId;
    private String headDoctorName;    // NOU
    private String phoneNumber;       // NOU

    public Department() {
    }

    public Department(String id, String name, String hospitalId, String headDoctorName, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.hospitalId = hospitalId;
        this.headDoctorName = headDoctorName;
        this.phoneNumber = phoneNumber;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getHospitalId() { return hospitalId; }
    public void setHospitalId(String hospitalId) { this.hospitalId = hospitalId; }

    public String getHeadDoctorName() { return headDoctorName; }
    public void setHeadDoctorName(String headDoctorName) { this.headDoctorName = headDoctorName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", hospitalId='" + hospitalId + '\'' +
                ", headDoctorName='" + headDoctorName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
