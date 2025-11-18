package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.*;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Validator {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s-]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{10,15}$");

    public void validatePatient(Patient patient) {
        validateName(patient.getName(), "Nume Pacient");
        validatePhone(patient.getPhoneNumber());

        if (patient.getHospitalId() == null || patient.getHospitalId().isEmpty()) {
            throw new IllegalArgumentException("Selectarea spitalului este obligatorie!");
        }
    }

    // ... Restul metodelor (validateDoctor, validateNurse etc.) rămân neschimbate ...
    public void validateDoctor(Doctor doctor) {
        validateName(doctor.getName(), "Nume Doctor");
        if (doctor.getLicenseNumber() == null || doctor.getLicenseNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Numărul de licență este obligatoriu!");
        }
    }

    public void validateNurse(Nurse nurse) {
        validateName(nurse.getName(), "Nume Asistent");
        if (nurse.getQualificationLevel() == null) {
            throw new IllegalArgumentException("Nivelul de calificare este obligatoriu!");
        }
    }

    public void validateHospital(Hospital hospital) {
        if (hospital.getName() == null || hospital.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Numele spitalului este obligatoriu!");
        }
        if (hospital.getCity() == null || hospital.getCity().trim().isEmpty()) {
            throw new IllegalArgumentException("Orașul este obligatoriu!");
        }
        validatePhone(hospital.getPhoneNumber());
    }

    public void validateDepartment(Department department) {
        if (department.getName() == null || department.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Numele departamentului este obligatoriu!");
        }
        if (department.getHospitalId() == null || department.getHospitalId().isEmpty()) {
            throw new IllegalArgumentException("Selectarea spitalului este obligatorie!");
        }
    }

    public void validateRoom(Room room) {
        if (room.getNumber() == null || room.getNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Numărul camerei este obligatoriu!");
        }
        if (room.getCapacity() <= 0) {
            throw new IllegalArgumentException("Capacitatea trebuie să fie mai mare de 0!");
        }
        if (room.getHospitalId() == null || room.getHospitalId().isEmpty()) {
            throw new IllegalArgumentException("Selectarea spitalului este obligatorie!");
        }
    }

    public void validateAppointment(Appointment appointment) {
        if (appointment.getAdmissionDate() == null) {
            throw new IllegalArgumentException("Data internării este obligatorie!");
        }
        if (appointment.getPatientId() == null || appointment.getPatientId().isEmpty()) {
            throw new IllegalArgumentException("Selectarea pacientului este obligatorie!");
        }
        if (appointment.getDepartmentId() == null || appointment.getDepartmentId().isEmpty()) {
            throw new IllegalArgumentException("Selectarea departamentului este obligatorie!");
        }
    }

    private void validateName(String name, String fieldName) {
        if (name == null || !NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(fieldName + " este invalid! (Sunt permise doar litere)");
        }
    }

    private void validatePhone(String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            if (!PHONE_PATTERN.matcher(phone).matches()) {
                throw new IllegalArgumentException("Număr de telefon invalid! (Doar cifre)");
            }
        }
    }
}