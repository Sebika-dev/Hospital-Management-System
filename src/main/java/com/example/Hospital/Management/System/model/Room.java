package com.example.Hospital.Management.System.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room extends BaseEntity {
    @NotBlank private String number;
    @Min(1) private int capacity;
    @Enumerated(EnumType.STRING) private RoomStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    @NotNull
    private Hospital hospital;

    // DIAGRAM CHANGE: Room lists Appointments
    @OneToMany(mappedBy = "room")
    private List<Appointment> appointments;

    public Room() {}
    public Room(String number, int capacity, RoomStatus status, Hospital hospital) {
        this.number = number; this.capacity = capacity; this.status = status; this.hospital = hospital;
    }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public RoomStatus getStatus() { return status; }
    public void setStatus(RoomStatus status) { this.status = status; }
    public Hospital getHospital() { return hospital; }
    public void setHospital(Hospital hospital) { this.hospital = hospital; }
    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
}