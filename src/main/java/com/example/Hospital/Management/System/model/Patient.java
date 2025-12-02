package com.example.Hospital.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient extends BaseEntity {
    @NotBlank private String name;
    private String phoneNumber;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    // DIAGRAM CHANGE: Patient does NOT have a room. Appointment has the room.

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    public Patient() {}
    public Patient(String name, String phoneNumber, Hospital hospital) {
        this.name = name; this.phoneNumber = phoneNumber; this.hospital = hospital;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Hospital getHospital() { return hospital; }
    public void setHospital(Hospital hospital) { this.hospital = hospital; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
}
