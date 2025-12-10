package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Patient;
import com.example.Hospital.Management.System.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientWebController {
    private final PatientService service;
    private final HospitalService hospitalService;
    private final DepartmentService departmentService;

    public PatientWebController(PatientService service, HospitalService hospitalService, DepartmentService departmentService) {
        this.service = service;
        this.hospitalService = hospitalService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String list(Model model,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) Long hospitalId,
                       @RequestParam(required = false) Long departmentId,
                       @RequestParam(required = false) String email,
                       @RequestParam(defaultValue = "name") String sortField,
                       @RequestParam(defaultValue = "asc") String sortDir) {

        var patients = service.getAllPatients(name, hospitalId, departmentId, email, sortField, sortDir);

        model.addAttribute("patients", patients);
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("departments", departmentService.getAllDepartments());

        // Parametrii pentru UI (filtre + sortare)
        model.addAttribute("filterName", name);
        model.addAttribute("filterHos", hospitalId);
        model.addAttribute("filterDept", departmentId);
        model.addAttribute("filterEmail", email);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "patient/index";
    }

    // ... Metodele create, edit, update, delete rămân neschimbate (le ai deja) ...
    @GetMapping("/new") public String createForm(Model model) {
        model.addAttribute("patient", new Patient());
        populateModel(model);
        return "patient/form";
    }
    @PostMapping public String create(@Valid @ModelAttribute Patient patient, BindingResult result, Model model) {
        if (result.hasErrors()) { populateModel(model); return "patient/form"; }
        service.savePatient(patient);
        return "redirect:/patients";
    }
    @GetMapping("/{id}/edit") public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", service.getPatientById(id).orElseThrow());
        populateModel(model);
        return "patient/form";
    }
    @PostMapping("/{id}") public String update(@PathVariable Long id, @Valid @ModelAttribute Patient patient, BindingResult result, Model model) {
        if (result.hasErrors()) { populateModel(model); return "patient/form"; }
        patient.setId(id);
        service.savePatient(patient);
        return "redirect:/patients";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable Long id) {
        service.deletePatient(id);
        return "redirect:/patients";
    }
    private void populateModel(Model model) {
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("departments", departmentService.getAllDepartments());
    }
}