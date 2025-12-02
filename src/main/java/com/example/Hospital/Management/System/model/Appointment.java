package com.example.Hospital.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity {
    @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate admissionDate;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @NotNull
    private Patient patient;

    // DIAGRAM CHANGE: Appointment is linked to Room
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @NotNull
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;

    public Appointment() {}
    public LocalDate getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(LocalDate admissionDate) { this.admissionDate = admissionDate; }
    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public Nurse getNurse() { return nurse; }
    public void setNurse(Nurse nurse) { this.nurse = nurse; }
}
