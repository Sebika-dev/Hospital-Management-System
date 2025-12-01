package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.MedicalStaffAppointment;
import com.example.Hospital.Management.System.service.MedicalStaffAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medical-staff-appointments")
public class MedicalStaffAppointmentWebController {

    private final MedicalStaffAppointmentService msaService;

    @Autowired
    public MedicalStaffAppointmentWebController(MedicalStaffAppointmentService msaService) {
        this.msaService = msaService;
    }

    @GetMapping
    public String listMedicalStaffAppointments(Model model) {
        model.addAttribute("msaList", msaService.getAllMedicalStaffAppointments());
        return "medical-staff-appointment/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("msa", new MedicalStaffAppointment());
        return "medical-staff-appointment/form";
    }

    @PostMapping
    public String createMedicalStaffAppointment(@ModelAttribute MedicalStaffAppointment msa) {
        msaService.addMedicalStaffAppointment(msa);
        return "redirect:/medical-staff-appointments";
    }

    @PostMapping("/{id}/delete")
    public String deleteMedicalStaffAppointment(@PathVariable String id) {
        msaService.deleteMedicalStaffAppointment(id);
        return "redirect:/medical-staff-appointments";
    }
}
