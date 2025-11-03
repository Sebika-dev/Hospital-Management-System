package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.MedicalStaffAppointment;
import com.example.Hospital.Management.System.service.MedicalStaffAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medical-staff-appointments")
public class MedicalStaffAppointmentController {

    private final MedicalStaffAppointmentService medicalStaffAppointmentService;

    @Autowired
    public MedicalStaffAppointmentController(MedicalStaffAppointmentService medicalStaffAppointmentService) {
        this.medicalStaffAppointmentService = medicalStaffAppointmentService;
    }

    @GetMapping
    public String getAllMedicalStaffAppointments(Model model) {
        model.addAttribute("medicalStaffAppointments", medicalStaffAppointmentService.getAllMedicalStaffAppointments());
        return "medical-staff-appointment/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("medicalStaffAppointment", new MedicalStaffAppointment());
        return "medical-staff-appointment/form";
    }

    @PostMapping
    public String createMedicalStaffAppointment(@ModelAttribute MedicalStaffAppointment link) {
        medicalStaffAppointmentService.saveMedicalStaffAppointment(link);
        return "redirect:/medical-staff-appointments";
    }

    @PostMapping("/{id}/delete")
    public String deleteMedicalStaffAppointment(@PathVariable String id) {
        medicalStaffAppointmentService.deleteMedicalStaffAppointment(id);
        return "redirect:/medical-staff-appointments";
    }
}