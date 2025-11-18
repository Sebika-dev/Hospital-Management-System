package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Patient;
import com.example.Hospital.Management.System.repository.infile.FilePatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService {

    private final FilePatientRepository patientRepository;
    private final RoomService roomService;
    private final Validator validator;

    @Autowired
    public PatientService(FilePatientRepository patientRepository,
                          RoomService roomService,
                          Validator validator) {
        this.patientRepository = patientRepository;
        this.roomService = roomService;
        this.validator = validator;
    }

    public Patient addPatient(Patient p) {
        validator.validatePatient(p);
        return patientRepository.save(p);
    }

    public Patient updatePatient(Patient updated) {
        validator.validatePatient(updated);
        patientRepository.findById(updated.getId()).ifPresent(old -> {
            if (!Objects.equals(old.getRoomId(), updated.getRoomId())) {
                // Scoate din camera veche
                if (old.getRoomId() != null) {
                    roomService.getRoomById(old.getRoomId()).ifPresent(r -> {
                        r.getAppointmentIds().removeAll(updated.getAppointmentIds());
                        roomService.updateRoom(r);
                    });
                }
                // Adaugă în camera nouă
                if (updated.getRoomId() != null) {
                    roomService.getRoomById(updated.getRoomId()).ifPresent(r -> {
                        for (String appId : updated.getAppointmentIds()) {
                            if (!r.getAppointmentIds().contains(appId)) {
                                r.getAppointmentIds().add(appId);
                            }
                        }
                        roomService.updateRoom(r);
                    });
                }
            }
        });
        return patientRepository.save(updated);
    }

    public void deletePatient(String id) {
        patientRepository.delete(id);
    }

    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}