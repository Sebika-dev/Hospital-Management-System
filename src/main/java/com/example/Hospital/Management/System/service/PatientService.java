package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Patient;
import com.example.Hospital.Management.System.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final Repository<Patient> patientRepository;

    @Autowired
    public PatientService(@Qualifier("inMemoryPatientRepository") Repository<Patient> patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public void deletePatient(String id) {
        patientRepository.delete(id);
    }

    public void deleteAllPatients() {
        patientRepository.deleteAll();
    }
}
