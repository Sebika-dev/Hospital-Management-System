package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Doctor;
import com.example.Hospital.Management.System.repository.inmemory.InMemoryDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final InMemoryDoctorRepository doctorRepository;

    @Autowired
    public DoctorService(@Qualifier("inMemoryDoctorRepository") InMemoryDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Doctor doctor) {
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
