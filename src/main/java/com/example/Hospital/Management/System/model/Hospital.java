package com.example.Hospital.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hospitals")
public class Hospital extends BaseEntity {
    @NotBlank(message = "Name is required") @Size(min = 3) private String name;
    @NotBlank private String city;
    @NotBlank private String address;
    private String phoneNumber;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Department> departments = new ArrayList<>();

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    public Hospital() {}
    public Hospital(String name, String city, String address, String phoneNumber) {
        this.name = name; this.city = city; this.address = address; this.phoneNumber = phoneNumber;
    }
    // Getters Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public List<Department> getDepartments() { return departments; }
    public void setDepartments(List<Department> departments) { this.departments = departments; }
    public List<Room> getRooms() { return rooms; }
    public void setRooms(List<Room> rooms) { this.rooms = rooms; }
}