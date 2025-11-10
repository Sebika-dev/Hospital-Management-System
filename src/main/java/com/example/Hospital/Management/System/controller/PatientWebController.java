package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Patient;
import com.example.Hospital.Management.System.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientWebController {
    private final PatientService patientService;

    @Autowired
    public PatientWebController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patient/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/form";
    }

    @PostMapping
    public String createPatient(@ModelAttribute Patient patient) {
        patientService.addPatient(patient);
        return "redirect:/patients";
    }

    @PostMapping("/{id}/delete")
    public String deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
