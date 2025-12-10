package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Department;
import com.example.Hospital.Management.System.service.DepartmentService;
import com.example.Hospital.Management.System.service.HospitalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentWebController {
    private final DepartmentService service;
    private final HospitalService hospitalService;
    @Autowired public DepartmentWebController(DepartmentService service, HospitalService hospitalService) {
        this.service = service; this.hospitalService = hospitalService;
    }

    @GetMapping
    public String list(Model model,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) Long hospitalId,
                       @RequestParam(defaultValue = "name") String sortField,
                       @RequestParam(defaultValue = "asc") String sortDir) {

        var departments = service.getAllDepartments(name, hospitalId, sortField, sortDir);

        model.addAttribute("departments", departments);
        model.addAttribute("hospitals", hospitalService.getAllHospitals());

        model.addAttribute("filterName", name);
        model.addAttribute("filterHos", hospitalId);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "department/index";
    }

    // ... Metode standard ...
    @GetMapping("/new") public String createForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "department/form";
    }
    @PostMapping public String create(@Valid @ModelAttribute Department department, BindingResult result, Model model) {
        if (result.hasErrors()) { model.addAttribute("hospitals", hospitalService.getAllHospitals()); return "department/form"; }
        service.saveDepartment(department);
        return "redirect:/departments";
    }
    @GetMapping("/{id}/edit") public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("department", service.getDepartmentById(id).orElseThrow());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "department/form";
    }
    @PostMapping("/{id}") public String update(@PathVariable Long id, @Valid @ModelAttribute Department department, BindingResult result, Model model) {
        if (result.hasErrors()) { model.addAttribute("hospitals", hospitalService.getAllHospitals()); return "department/form"; }
        department.setId(id);
        service.saveDepartment(department);
        return "redirect:/departments";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable Long id) {
        service.deleteDepartment(id);
        return "redirect:/departments";
    }
}