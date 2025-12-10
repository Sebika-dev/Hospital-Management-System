package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Doctor;
import com.example.Hospital.Management.System.service.DepartmentService;
import com.example.Hospital.Management.System.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctors")
public class DoctorWebController {
    private final DoctorService service;
    private final DepartmentService departmentService;
    @Autowired public DoctorWebController(DoctorService service, DepartmentService departmentService) {
        this.service = service; this.departmentService = departmentService;
    }

    @GetMapping
    public String list(Model model,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) Long departmentId,
                       @RequestParam(defaultValue = "name") String sortField,
                       @RequestParam(defaultValue = "asc") String sortDir) {

        var doctors = service.getAllDoctors(name, departmentId, sortField, sortDir);

        model.addAttribute("doctors", doctors);
        model.addAttribute("departments", departmentService.getAllDepartments());

        model.addAttribute("filterName", name);
        model.addAttribute("filterDept", departmentId);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "doctor/index";
    }

    @GetMapping("/new") public String createForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "doctor/form";
    }
    @PostMapping public String create(@Valid @ModelAttribute Doctor doctor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "doctor/form";
        }
        service.saveDoctor(doctor);
        return "redirect:/doctors";
    }
    @GetMapping("/{id}/edit") public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", service.getDoctorById(id).orElseThrow());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "doctor/form";
    }
    @PostMapping("/{id}") public String update(@PathVariable Long id, @Valid @ModelAttribute Doctor doctor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "doctor/form";
        }
        doctor.setId(id);
        service.saveDoctor(doctor);
        return "redirect:/doctors";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable Long id) {
        service.deleteDoctor(id);
        return "redirect:/doctors";
    }
}