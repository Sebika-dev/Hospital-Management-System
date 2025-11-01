package com.example.Hospital_Management.System.service;

import com.example.Hospital_Management.System.model.MedicalStaffAppointment;
import com.example.Hospital_Management.System.repository.MedicalStaffAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalStaffAppointmentService {

    private final MedicalStaffAppointmentRepository medicalStaffAppointmentRepository;

    @Autowired
    public MedicalStaffAppointmentService(MedicalStaffAppointmentRepository medicalStaffAppointmentRepository) {
        this.medicalStaffAppointmentRepository = medicalStaffAppointmentRepository;
    }

    public MedicalStaffAppointment saveMedicalStaffAppointment(MedicalStaffAppointment link) {
        return medicalStaffAppointmentRepository.save(link);
    }

    public List<MedicalStaffAppointment> getAllMedicalStaffAppointments() {
        return medicalStaffAppointmentRepository.findAll();
    }

    public MedicalStaffAppointment getMedicalStaffAppointmentById(String id) {
        return medicalStaffAppointmentRepository.findById(id);
    }

    public void deleteMedicalStaffAppointment(String id) {
        medicalStaffAppointmentRepository.delete(id);
    }
}