package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Hospital;
import com.example.Hospital.Management.System.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    private final Repository<Hospital> hospitalRepository;
    private final Validator validator;

    @Autowired
    public HospitalService(@Qualifier("fileHospitalRepository") Repository<Hospital> hospitalRepository,
                           Validator validator) {
        this.hospitalRepository = hospitalRepository;
        this.validator = validator;
    }

    public Hospital addHospital(Hospital hospital) {
        validator.validateHospital(hospital);
        return hospitalRepository.save(hospital);
    }

    public Hospital updateHospital(Hospital hospital) {
        validator.validateHospital(hospital);
        return hospitalRepository.save(hospital);
    }

    public Optional<Hospital> getHospitalById(String id) {
        return hospitalRepository.findById(id);
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public void deleteHospital(String id) {
        hospitalRepository.delete(id);
    }

    public void deleteAllHospitals() {
        hospitalRepository.deleteAll();
    }
}