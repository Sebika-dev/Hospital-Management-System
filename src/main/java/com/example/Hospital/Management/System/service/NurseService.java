package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Nurse;
import com.example.Hospital.Management.System.repository.infile.FileNurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NurseService {
    private final FileNurseRepository nurseRepository;
    private final Validator validator;

    @Autowired
    public NurseService(FileNurseRepository nurseRepository, Validator validator) {
        this.nurseRepository = nurseRepository;
        this.validator = validator;
    }

    public Nurse addNurse(Nurse nurse) {
        validator.validateNurse(nurse);
        return nurseRepository.save(nurse);
    }

    public Nurse updateNurse(Nurse nurse) {
        validator.validateNurse(nurse);
        return nurseRepository.save(nurse);
    }

    // Metodă sigură
    public void addAppointmentToNurse(String nurseId, String appointmentId) {
        getNurseById(nurseId).ifPresent(n -> {
            if (!n.getAppointmentIds().contains(appointmentId)) {
                n.addAppointment(appointmentId);
                nurseRepository.save(n);
            }
        });
    }

    public Optional<Nurse> getNurseById(String id) { return nurseRepository.findById(id); }
    public List<Nurse> getAllNurses() { return nurseRepository.findAll(); }
    public List<Nurse> getNursesByDepartmentId(String departmentId) { return nurseRepository.findByDepartmentId(departmentId); }
    public void deleteNurse(String id) { nurseRepository.delete(id); }
    public void deleteAllNurses() { nurseRepository.deleteAll(); }
}