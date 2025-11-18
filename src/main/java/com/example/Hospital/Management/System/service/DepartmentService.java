package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Department;
import com.example.Hospital.Management.System.repository.infile.FileDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final FileDepartmentRepository departmentRepository;
    private final HospitalService hospitalService;
    private final Validator validator;

    @Autowired
    public DepartmentService(FileDepartmentRepository departmentRepository,
                             @Lazy HospitalService hospitalService,
                             Validator validator) {
        this.departmentRepository = departmentRepository;
        this.hospitalService = hospitalService;
        this.validator = validator;
    }

    public Department addDepartment(Department department) {
        validator.validateDepartment(department);

        Department saved = departmentRepository.save(department);
        if (saved.getHospitalId() != null) {
            hospitalService.getHospitalById(saved.getHospitalId()).ifPresent(h -> {
                if (!h.getDepartmentIds().contains(saved.getId())) {
                    h.addDepartment(saved.getId());
                    // Update intern la spital, nu revalidăm spitalul complet aici
                    hospitalService.getHospitalById(h.getId()).ifPresent(current -> {
                        // Forțăm salvarea directă prin repository dacă e nevoie,
                        // sau folosim updateHospital dacă datele spitalului sunt valide curent
                        hospitalService.updateHospital(h);
                    });
                }
            });
        }
        return saved;
    }

    public Department updateDepartment(Department department) {
        validator.validateDepartment(department);
        return departmentRepository.save(department);
    }

    public Optional<Department> getDepartmentById(String id) {
        return departmentRepository.findById(id);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public List<Department> getDepartmentsByHospitalId(String hospitalId) {
        return departmentRepository.findByHospitalId(hospitalId);
    }

    public void deleteDepartment(String id) {
        departmentRepository.findById(id).ifPresent(dept -> {
            if (dept.getHospitalId() != null) {
                hospitalService.getHospitalById(dept.getHospitalId()).ifPresent(h -> {
                    h.getDepartmentIds().remove(id);
                    hospitalService.updateHospital(h);
                });
            }
        });
        departmentRepository.delete(id);
    }

    public void deleteAllDepartments() {
        departmentRepository.deleteAll();
    }
}