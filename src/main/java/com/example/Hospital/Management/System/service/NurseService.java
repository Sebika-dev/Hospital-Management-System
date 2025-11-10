package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Nurse;
import com.example.Hospital.Management.System.repository.inmemory.InMemoryNurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NurseService {
    private final InMemoryNurseRepository nurseRepository;

    @Autowired
    public NurseService(InMemoryNurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    public Nurse addNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    public Nurse updateNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    public Optional<Nurse> getNurseById(String id) {
        return nurseRepository.findById(id);
    }

    public List<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }

    public List<Nurse> getNursesByDepartmentId(String departmentId) {
        return nurseRepository.findByDepartmentId(departmentId);
    }

    public void deleteNurse(String id) {
        nurseRepository.delete(id);
    }

    public void deleteAllNurses() {
        nurseRepository.deleteAll();
    }

    // Helper opțional
    public void addAppointmentToNurse(String nurseId, String appointmentId) {
        getNurseById(nurseId).ifPresent(n -> {
            if (!n.getAppointmentIds().contains(appointmentId)) {
                n.addAppointment(appointmentId);
                updateNurse(n);
            }
        });
    }
}
