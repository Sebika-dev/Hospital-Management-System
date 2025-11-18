package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Doctor;
import com.example.Hospital.Management.System.repository.infile.FileDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final FileDoctorRepository doctorRepository;
    private final Validator validator;

    @Autowired
    public DoctorService(FileDoctorRepository doctorRepository, Validator validator) {
        this.doctorRepository = doctorRepository;
        this.validator = validator;
    }

    public Doctor addDoctor(Doctor doctor) {
        validator.validateDoctor(doctor);
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Doctor doctor) {
        validator.validateDoctor(doctor);
        return doctorRepository.save(doctor);
    }

    public Optional<Doctor> getDoctorById(String id) {
        return doctorRepository.findById(id);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<Doctor> getDoctorsByDepartmentId(String departmentId) {
        return doctorRepository.findByDepartmentId(departmentId);
    }

    public void deleteDoctor(String id) {
        doctorRepository.delete(id);
    }

    public void deleteAllDoctors() {
        doctorRepository.deleteAll();
    }
}