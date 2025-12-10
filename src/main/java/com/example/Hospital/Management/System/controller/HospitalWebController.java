package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Hospital;
import com.example.Hospital.Management.System.service.HospitalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hospitals")
public class HospitalWebController {
    private final HospitalService service;
    @Autowired public HospitalWebController(HospitalService service) { this.service = service; }

    @GetMapping
    public String list(Model model,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) String city,
                       @RequestParam(defaultValue = "name") String sortField,
                       @RequestParam(defaultValue = "asc") String sortDir) {

        var hospitals = service.getAllHospitals(name, city, sortField, sortDir);

        model.addAttribute("hospitals", hospitals);
        model.addAttribute("filterName", name);
        model.addAttribute("filterCity", city);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "hospital/index";
    }

    // ... Metode standard (create, edit, delete) ...
    @GetMapping("/new") public String createForm(Model model) {
        model.addAttribute("hospital", new Hospital());
        return "hospital/form";
    }
    @PostMapping public String create(@Valid @ModelAttribute Hospital hospital, BindingResult result) {
        if (result.hasErrors()) return "hospital/form";
        service.saveHospital(hospital);
        return "redirect:/hospitals";
    }
    @GetMapping("/{id}/edit") public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("hospital", service.getHospitalById(id).orElseThrow());
        return "hospital/form";
    }
    @PostMapping("/{id}") public String update(@PathVariable Long id, @Valid @ModelAttribute Hospital hospital, BindingResult result) {
        if (result.hasErrors()) return "hospital/form";
        hospital.setId(id);
        service.saveHospital(hospital);
        return "redirect:/hospitals";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable Long id) {
        service.deleteHospital(id);
        return "redirect:/hospitals";
    }
}