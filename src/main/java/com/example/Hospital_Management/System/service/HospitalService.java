package com.example.Hospital_Management.System.service;

import com.example.Hospital_Management.System.model.Hospital;
import com.example.Hospital_Management.System.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Hospital saveHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public List<Hospital> getAllHospital() {
        return hospitalRepository.findAll();
    }

    public Hospital getHospitalById(String id) {
        return hospitalRepository.findById(id);
    }

    public void deleteHospitalById(String id) {
        hospitalRepository.delete(id);
    }
}

