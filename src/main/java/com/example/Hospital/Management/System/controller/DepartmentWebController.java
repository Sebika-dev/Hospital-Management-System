package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Department;
import com.example.Hospital.Management.System.model.Hospital;
import com.example.Hospital.Management.System.service.DepartmentService;
import com.example.Hospital.Management.System.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/departments")
public class DepartmentWebController {
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @Autowired
    public DepartmentWebController(DepartmentService departmentService,
                                   HospitalService hospitalService) {
        this.departmentService = departmentService;
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String listDepartments(Model model) {
        var departments = departmentService.getAllDepartments();
        Map<String,String> hospitalNames = hospitalService.getAllHospitals()
                .stream().collect(Collectors.toMap(Hospital::getId, Hospital::getName));
        model.addAttribute("departments", departments);
        model.addAttribute("hospitalNames", hospitalNames);
        return "department/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "department/form";
    }

    @PostMapping
    public String createDepartment(@ModelAttribute Department department) {
        departmentService.addDepartment(department);
        return "redirect:/departments";
    }

    @PostMapping("/{id}/delete")
    public String deleteDepartment(@PathVariable String id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}
