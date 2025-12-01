package com.example.Hospital.Management.System.controller;
import com.example.Hospital.Management.System.model.MedicalStaffAppointment;
import com.example.Hospital.Management.System.service.AppointmentService;
import com.example.Hospital.Management.System.service.MedicalStaffAppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medical-staff-appointments")
public class MedicalStaffAppointmentWebController {
    private final MedicalStaffAppointmentService service;
    private final AppointmentService appointmentService;
    @Autowired public MedicalStaffAppointmentWebController(MedicalStaffAppointmentService service, AppointmentService appointmentService) {
        this.service = service; this.appointmentService = appointmentService;
    }
    @GetMapping public String list(Model model) {
        model.addAttribute("msaList", service.getAll());
        return "medical-staff-appointment/index";
    }
    @GetMapping("/new") public String createForm(Model model) {
        model.addAttribute("msa", new MedicalStaffAppointment());
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "medical-staff-appointment/form";
    }
    @PostMapping public String create(@Valid @ModelAttribute("msa") MedicalStaffAppointment msa, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("appointments", appointmentService.getAllAppointments());
            return "medical-staff-appointment/form";
        }
        service.save(msa);
        return "redirect:/medical-staff-appointments";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/medical-staff-appointments";
    }
}