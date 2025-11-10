package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.MedicalStaffAppointment;
import com.example.Hospital.Management.System.repository.inmemory.InMemoryMedicalStaffAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalStaffAppointmentService {
    private final InMemoryMedicalStaffAppointmentRepository msaRepository;

    @Autowired
    public MedicalStaffAppointmentService(@Qualifier("inMemoryMedicalStaffAppointmentRepository") InMemoryMedicalStaffAppointmentRepository msaRepository) {
        this.msaRepository = msaRepository;
    }

    public MedicalStaffAppointment addMedicalStaffAppointment(MedicalStaffAppointment msa) {
        return msaRepository.save(msa);
    }

    public Optional<MedicalStaffAppointment> getMedicalStaffAppointmentById(String id) {
        return msaRepository.findById(id);
    }

    public List<MedicalStaffAppointment> getAllMedicalStaffAppointments() {
        return msaRepository.findAll();
    }

    public List<MedicalStaffAppointment> getMedicalStaffAppointmentsByAppointmentId(String appointmentId) {
        return msaRepository.findByAppointmentId(appointmentId);
    }

    public List<MedicalStaffAppointment> getMedicalStaffAppointmentsByMedicalStaffId(String medicalStaffId) {
        return msaRepository.findByMedicalStaffId(medicalStaffId);
    }

    public void deleteMedicalStaffAppointment(String id) {
        msaRepository.delete(id);
    }

    public void deleteAllMedicalStaffAppointments() {
        msaRepository.deleteAll();
    }
}
