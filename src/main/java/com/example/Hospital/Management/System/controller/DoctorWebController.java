package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Department;
import com.example.Hospital.Management.System.model.Doctor;
import com.example.Hospital.Management.System.service.DepartmentService;
import com.example.Hospital.Management.System.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/doctors")
public class DoctorWebController {
    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    @Autowired
    public DoctorWebController(DoctorService doctorService,
                               DepartmentService departmentService) {
        this.doctorService = doctorService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String listDoctors(Model model) {
        var doctors = doctorService.getAllDoctors();
        Map<String,String> departmentNames = departmentService.getAllDepartments()
                .stream().collect(Collectors.toMap(Department::getId, Department::getName));
        model.addAttribute("doctors", doctors);
        model.addAttribute("departmentNames", departmentNames);
        return "doctor/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "doctor/form";
    }

    @PostMapping
    public String createDoctor(@ModelAttribute Doctor doctor) {
        doctorService.addDoctor(doctor);
        return "redirect:/doctors";
    }

    @PostMapping("/{id}/delete")
    public String deleteDoctor(@PathVariable String id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }
}
