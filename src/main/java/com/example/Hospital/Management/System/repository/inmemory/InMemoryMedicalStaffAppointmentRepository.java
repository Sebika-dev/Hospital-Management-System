package com.example.Hospital.Management.System.repository.inmemory;

import com.example.Hospital.Management.System.model.MedicalStaffAppointment;
import com.example.Hospital.Management.System.repository.IdGenerator;
import com.example.Hospital.Management.System.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component("inMemoryMedicalStaffAppointmentRepository")
public class InMemoryMedicalStaffAppointmentRepository implements Repository<MedicalStaffAppointment> {
    private final Map<String, MedicalStaffAppointment> storage = new ConcurrentHashMap<>();

    @Override
    public MedicalStaffAppointment save(MedicalStaffAppointment msa) {
        if (msa.getId() == null || msa.getId().isEmpty()) {
            msa.setId(IdGenerator.generateId("MSA"));
        }
        storage.put(msa.getId(), msa);
        return msa;
    }

    @Override
    public Optional<MedicalStaffAppointment> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<MedicalStaffAppointment> findAll() {
        return new ArrayList<>(storage.values());
    }

    public List<MedicalStaffAppointment> findByAppointmentId(String appointmentId) {
        return storage.values().stream()
                .filter(msa -> msa.getAppointmentId().equals(appointmentId))
                .collect(Collectors.toList());
    }

    public List<MedicalStaffAppointment> findByMedicalStaffId(String medicalStaffId) {
        return storage.values().stream()
                .filter(msa -> msa.getMedicalStaffId().equals(medicalStaffId))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }
}
