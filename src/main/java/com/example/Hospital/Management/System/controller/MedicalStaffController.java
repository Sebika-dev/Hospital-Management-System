package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Doctor;
import com.example.Hospital.Management.System.model.MedicalStaff;
import com.example.Hospital.Management.System.model.Nurse;
import com.example.Hospital.Management.System.service.MedicalStaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medical-staff")
public class MedicalStaffController {

    private final MedicalStaffService staffService;

    public MedicalStaffController(MedicalStaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public String listAll(Model model) {
        List<MedicalStaff> list = staffService.findAll();
        model.addAttribute("staff", list);
        return "medicalstaff/index"; // Corespunde cu templates/medicalstaff/index.html
    }

    @GetMapping("/new")
    public String createForm(@RequestParam(value = "type", required = false, defaultValue = "DOCTOR") String type, Model model) {
        MedicalStaff staff = "NURSE".equalsIgnoreCase(type) ? new Nurse() : new Doctor();
        model.addAttribute("medicalStaff", staff);
        model.addAttribute("type", type.toUpperCase());
        return "medicalstaff/medicalstaff-form"; // Corespunde cu templates/medicalstaff/medicalstaff-form.html
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        MedicalStaff staff = staffService.findById(id);
        if (staff == null) return "redirect:/medical-staff";
        model.addAttribute("medicalStaff", staff);
        model.addAttribute("type", staff instanceof Nurse ? "NURSE" : "DOCTOR");
        return "medicalstaff/medicalstaff-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("medicalStaff") MedicalStaff staff,
                       @RequestParam("type") String type,
                       @RequestParam("extra") String extra,
                       @RequestParam(value = "appointmentIds", required = false) String[] appointmentIds) {
        if ("NURSE".equalsIgnoreCase(type)) {
            Nurse nurse = (Nurse) staff;
            nurse.setQualificationLevel(extra);
        } else {
            Doctor doctor = (Doctor) staff;
            doctor.setLicenseNumber(extra);
        }
        if (appointmentIds != null) {
            staff.setAppointmentIds(List.of(appointmentIds));
        }
        staffService.save(staff);
        return "redirect:/medical-staff";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        staffService.deleteById(id);
        return "redirect:/medical-staff";
    }
}