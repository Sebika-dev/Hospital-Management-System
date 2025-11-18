package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Patient;
import com.example.Hospital.Management.System.model.Room;
import com.example.Hospital.Management.System.model.Department;
import com.example.Hospital.Management.System.model.Hospital;
import com.example.Hospital.Management.System.service.PatientService;
import com.example.Hospital.Management.System.service.RoomService;
import com.example.Hospital.Management.System.service.DepartmentService;
import com.example.Hospital.Management.System.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/patients")
public class PatientWebController {
    private final PatientService patientService;
    private final RoomService roomService;
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @Autowired
    public PatientWebController(PatientService patientService,
                                RoomService roomService,
                                DepartmentService departmentService,
                                HospitalService hospitalService) {
        this.patientService = patientService;
        this.roomService = roomService;
        this.departmentService = departmentService;
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());

        Map<String,String> roomLabels = roomService.getAllRooms()
                .stream().collect(Collectors.toMap(Room::getId, r -> "Room " + r.getNumber()));
        Map<String,String> departmentNames = departmentService.getAllDepartments()
                .stream().collect(Collectors.toMap(Department::getId, Department::getName));
        // Map pentru numele spitalelor (opțional, pentru tabel)
        Map<String,String> hospitalNames = hospitalService.getAllHospitals()
                .stream().collect(Collectors.toMap(Hospital::getId, Hospital::getName));

        model.addAttribute("roomLabels", roomLabels);
        model.addAttribute("departmentNames", departmentNames);
        model.addAttribute("hospitalNames", hospitalNames);
        return "patient/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("patient", new Patient());
        populateFormData(model);
        return "patient/form";
    }

    @PostMapping
    public String createPatient(@ModelAttribute Patient patient, Model model) {
        try {
            patientService.addPatient(patient);
            return "redirect:/patients";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            populateFormData(model);
            return "patient/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editPatient(@PathVariable String id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id).orElseThrow());
        populateFormData(model);
        return "patient/form";
    }

    @PostMapping("/{id}")
    public String updatePatient(@PathVariable String id, @ModelAttribute Patient patient, Model model) {
        try {
            patient.setId(id);
            patientService.updatePatient(patient);
            return "redirect:/patients";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            populateFormData(model);
            return "patient/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    private void populateFormData(Model model) {
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        // Trimitem listele complete inițial, dar JS le va filtra
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("departments", departmentService.getAllDepartments());
    }
}