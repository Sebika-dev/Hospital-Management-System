package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Patient;
import com.example.Hospital.Management.System.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepo; // Poate fi inutil dacă nu e JPA

    private ArrayList<Patient> patients = new ArrayList<>();

    @GetMapping("/patients")
    public String getAllPatients(Model model) {
        model.addAttribute("patients", patients);
        return "patient/index";
    }

    @GetMapping("/patients/new")
    public String showPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/form";
    }

    @PostMapping("/patients")
    public String createPatient(@ModelAttribute Patient patient) {
        patients.add(patient); // Adăugare în listă în memorie
        System.out.println("Salvat pacient: " + patient.getName());
        return "redirect:/patients";
    }
}