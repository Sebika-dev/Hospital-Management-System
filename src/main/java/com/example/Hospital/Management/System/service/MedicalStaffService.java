package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.MedicalStaff;
import com.example.Hospital.Management.System.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalStaffService {

    private final MedicalStaffRepository medicalStaffRepository;

    @Autowired
    public MedicalStaffService(MedicalStaffRepository medicalStaffRepository) {
        this.medicalStaffRepository = medicalStaffRepository;
    }

    public MedicalStaff saveMedicalStaff(MedicalStaff medicalStaff) {
        return medicalStaffRepository.save(medicalStaff);
    }

    public List<MedicalStaff> getAllMedicalStaff() {
        return medicalStaffRepository.findAll();
    }

    public MedicalStaff getMedicalStaffById(String id) {
        return medicalStaffRepository.findById(id);
    }

    public void deleteMedicalStaff(String id) {
        medicalStaffRepository.delete(id);
    }
}