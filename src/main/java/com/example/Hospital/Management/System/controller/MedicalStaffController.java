package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.MedicalStaff;
import com.example.Hospital.Management.System.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medical-staff")
public class MedicalStaffController {

    private final MedicalStaffService medicalStaffService;

    @Autowired
    public MedicalStaffController(MedicalStaffService medicalStaffService) {
        this.medicalStaffService = medicalStaffService;
    }

    @GetMapping
    public String getAllMedicalStaff(Model model) {
        model.addAttribute("medicalStaff", medicalStaffService.getAllMedicalStaff());
        return "medical-staff/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("medicalStaff", new MedicalStaff());
        return "medical-staff/form";
    }

    @PostMapping
    public String createMedicalStaff(@ModelAttribute MedicalStaff medicalStaff) {
        medicalStaffService.saveMedicalStaff(medicalStaff);
        return "redirect:/medical-staff";
    }

    @PostMapping("/{id}/delete")
    public String deleteMedicalStaff(@PathVariable String id) {
        medicalStaffService.deleteMedicalStaff(id);
        return "redirect:/medical-staff";
    }
}