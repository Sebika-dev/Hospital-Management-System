package com.example.Hospital.Management.System;

import com.example.Hospital.Management.System.model.*;
import com.example.Hospital.Management.System.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class HospitalManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalManagementSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(HospitalRepository hospitalRepo,
                                  DepartmentRepository deptRepo,
                                  PatientRepository patientRepo,
                                  RoomRepository roomRepo,
                                  MedicalStaffRepository staffRepo,
                                  AppointmentRepository appRepo,
                                  MedicalStaffAppointmentRepository linkRepo) {
        return args -> {
            // Populează Hospital
            Hospital h1 = new Hospital("H1", "Spital Central", "București");
            Hospital h2 = new Hospital("H2", "Spital Nord", "Cluj-Napoca");
            hospitalRepo.save(h1);
            hospitalRepo.save(h2);

            // Populează Department
            Department d1 = new Department("D1", "Cardiologie", "H1");
            Department d2 = new Department("D2", "Pediatrie", "H2");
            deptRepo.save(d1);
            deptRepo.save(d2);

            // Populează Patient
            Patient p1 = new Patient("P1", "Ion Popescu");
            p1.setDateOfBirth(LocalDate.of(1995, 11, 2)); // Aproximativ 30 de ani
            p1.setPhoneNumber("0723456789");
            Patient p2 = new Patient("P2", "Maria Ionescu");
            p2.setDateOfBirth(LocalDate.of(2000, 11, 2)); // Aproximativ 25 de ani
            p2.setPhoneNumber("0734567890");
            patientRepo.save(p1);
            patientRepo.save(p2);

            // Populează Room
            Room r1 = new Room("R1", "H1", "101", 2.0, "Available");
            r1.setType("General");
            r1.setHasMonitoringEquipment(true);
            Room r2 = new Room("R2", "H2", "202", 4.0, "Occupied");
            r2.setType("ICU");
            r2.setHasMonitoringEquipment(false);
            roomRepo.save(r1);
            roomRepo.save(r2);

            // Populează MedicalStaff
            Doctor ms1 = new Doctor("MS1", "Dr. Ana Maria", "D1", "Cardiologie", Arrays.asList(
                    new Appointment(
                    "A002",
                    "D2",
                    "P002",
                    LocalDate.of(2025, 11, 4),
                    "Active"
                    )));
            Nurse ms2 = new Nurse("MS2", "Sorina Popa", "D2", "Zi", Arrays.asList(
                    new Appointment(
                            "A001", // id
                            "D2",   // departmentId
                            "P001", // patientId
                            LocalDate.of(2025, 11, 3), // admissionDate
                            "Active" // status
                    )));
            staffRepo.save(ms1);
            staffRepo.save(ms2);

            // Populează Appointment
            Appointment a1 = new Appointment("A1", "D1", "P1", LocalDate.of(2025, 11, 3), "Active");
            Appointment a2 = new Appointment("A2", "D2", "P2", LocalDate.of(2025, 11, 4), "Active");
            appRepo.save(a1);
            appRepo.save(a2);

            // Populează MedicalStaffAppointment
            MedicalStaffAppointment link1 = new MedicalStaffAppointment("L1", "MS1", "A1");
            MedicalStaffAppointment link2 = new MedicalStaffAppointment("L2", "MS2", "A2");
            linkRepo.save(link1);
            linkRepo.save(link2);
        };
    }
}