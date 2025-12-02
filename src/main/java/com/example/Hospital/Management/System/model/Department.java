package com.example.Hospital.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department extends BaseEntity {
    @NotBlank private String name;
    private String headDoctorName;
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    @NotNull
    private Hospital hospital;

    @OneToMany(mappedBy = "department")
    private List<Doctor> doctors = new ArrayList<>();

    @OneToMany(mappedBy = "department")
    private List<Nurse> nurses = new ArrayList<>();

    public Department() {}
    public Department(String name, Hospital hospital) { this.name = name; this.hospital = hospital; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getHeadDoctorName() { return headDoctorName; }
    public void setHeadDoctorName(String headDoctorName) { this.headDoctorName = headDoctorName; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public Hospital getHospital() { return hospital; }
    public void setHospital(Hospital hospital) { this.hospital = hospital; }
    public List<Doctor> getDoctors() { return doctors; }
    public void setDoctors(List<Doctor> doctors) { this.doctors = doctors; }
    public List<Nurse> getNurses() { return nurses; }
    public void setNurses(List<Nurse> nurses) { this.nurses = nurses; }
}
