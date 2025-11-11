package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Patient;
import com.example.Hospital.Management.System.repository.inmemory.InMemoryPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService {

    private final InMemoryPatientRepository patientRepository;
    private final RoomService roomService;

    @Autowired
    public PatientService(InMemoryPatientRepository patientRepository,
                          RoomService roomService) {
        this.patientRepository = patientRepository;
        this.roomService = roomService;
    }

    public Patient addPatient(Patient p) {
        return patientRepository.save(p);
    }

    public Patient updatePatient(Patient updated) {
        patientRepository.findById(updated.getId()).ifPresent(old -> {
            // dacă s-a schimbat camera pacientului, mutăm toate programările în noua cameră
            if (!Objects.equals(old.getRoomId(), updated.getRoomId())) {
                // scoate programările din vechea cameră
                if (old.getRoomId() != null) {
                    roomService.getRoomById(old.getRoomId()).ifPresent(r -> {
                        r.getAppointmentIds().removeAll(updated.getAppointmentIds());
                        roomService.updateRoom(r);
                    });
                }
                // adaugă programările în noua cameră
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
        // Notă: aici nu ștergem appointments; presupunem că se face separat
        patientRepository.delete(id);
    }

    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
