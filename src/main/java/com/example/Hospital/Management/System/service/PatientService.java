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
    private final DepartmentService departmentService;
    private final Validator validator;

    @Autowired
    public PatientService(FilePatientRepository patientRepository,
                          RoomService roomService,
                          DepartmentService departmentService,
                          Validator validator) {
        this.patientRepository = patientRepository;
        this.roomService = roomService;
        this.departmentService = departmentService;
        this.validator = validator;
    }

    public Patient addPatient(Patient p) {
        validator.validatePatient(p);
        validateHospitalConsistency(p);
        return patientRepository.save(p);
    }

    public Patient updatePatient(Patient updated) {
        validator.validatePatient(updated);
        validateHospitalConsistency(updated);

        patientRepository.findById(updated.getId()).ifPresent(old -> {
            if (!Objects.equals(old.getRoomId(), updated.getRoomId())) {
                if (old.getRoomId() != null) {
                    roomService.getRoomById(old.getRoomId()).ifPresent(r -> {
                        r.getAppointmentIds().removeAll(updated.getAppointmentIds());
                        roomService.updateRoom(r);
                    });
                }
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

    // METODĂ NOUĂ: Adaugă programare fără validare completă a datelor pacientului
    // (utilă pentru a nu bloca fluxul dacă pacientul are date vechi ușor incomplete)
    public void addAppointmentToPatient(String patientId, String appointmentId) {
        patientRepository.findById(patientId).ifPresent(p -> {
            if (!p.getAppointmentIds().contains(appointmentId)) {
                p.getAppointmentIds().add(appointmentId);
                patientRepository.save(p); // Salvare directă
            }
        });
    }

    public void removeAppointmentFromPatient(String patientId, String appointmentId) {
        patientRepository.findById(patientId).ifPresent(p -> {
            if (p.getAppointmentIds().remove(appointmentId)) {
                patientRepository.save(p); // Salvare directă
            }
        });
    }

    private void validateHospitalConsistency(Patient p) {
        String hosId = p.getHospitalId();
        if (p.getRoomId() != null && !p.getRoomId().isEmpty()) {
            roomService.getRoomById(p.getRoomId()).ifPresent(room -> {
                if (!Objects.equals(room.getHospitalId(), hosId)) {
                    throw new IllegalArgumentException("Camera selectată nu aparține spitalului ales!");
                }
            });
        }
        if (p.getDepartmentId() != null && !p.getDepartmentId().isEmpty()) {
            departmentService.getDepartmentById(p.getDepartmentId()).ifPresent(dept -> {
                if (!Objects.equals(dept.getHospitalId(), hosId)) {
                    throw new IllegalArgumentException("Departamentul selectat nu aparține spitalului ales!");
                }
            });
        }
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