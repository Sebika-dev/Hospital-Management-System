package com.example.Hospital.Management.System.repository;

import com.example.Hospital.Management.System.model.MedicalStaffAppointment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MedicalStaffAppointmentRepository {
    private final Map<String, MedicalStaffAppointment> links = new HashMap<>();

    public MedicalStaffAppointment save(MedicalStaffAppointment link) {
        links.put(link.getId(), link);
        return link;
    }

    public List<MedicalStaffAppointment> findAll() {
        return new ArrayList<>(links.values());
    }

    public MedicalStaffAppointment findById(String id) {
        return links.get(id);
    }

    public void delete(String id) {
        links.remove(id);
    }
}
