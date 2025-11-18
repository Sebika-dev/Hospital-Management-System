package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Hospital;
import com.example.Hospital.Management.System.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hospitals")
public class HospitalWebController {
    private final HospitalService hospitalService;

    @Autowired
    public HospitalWebController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String listHospitals(Model model) {
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "hospital/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("hospital", new Hospital());
        return "hospital/form";
    }

    @PostMapping
    public String createHospital(@ModelAttribute Hospital hospital) {
        hospitalService.addHospital(hospital);
        return "redirect:/hospitals";
    }

    @PostMapping("/{id}/delete")
    public String deleteHospital(@PathVariable String id) {
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals";
    }

    @GetMapping("/{id}/edit")
    public String editHospital(@PathVariable String id, Model model) {
        Hospital hospital = hospitalService.getHospitalById(id).orElseThrow();
        model.addAttribute("hospital", hospital);
        return "hospital/form";
    }

    @PostMapping("/{id}")
    public String updateHospital(@PathVariable String id, @ModelAttribute Hospital hospital) {
        hospital.setId(id);
        hospitalService.updateHospital(hospital);
        return "redirect:/hospitals";
    }

}
