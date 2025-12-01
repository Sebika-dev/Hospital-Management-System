package com.example.Hospital.Management.System.controller;
import com.example.Hospital.Management.System.model.Nurse;
import com.example.Hospital.Management.System.model.NurseQualificationLevel;
import com.example.Hospital.Management.System.service.DepartmentService;
import com.example.Hospital.Management.System.service.NurseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nurses")
public class NurseWebController {
    private final NurseService service;
    private final DepartmentService departmentService;
    @Autowired public NurseWebController(NurseService service, DepartmentService departmentService) {
        this.service = service; this.departmentService = departmentService;
    }
    @GetMapping public String list(Model model) {
        model.addAttribute("nurses", service.getAllNurses());
        return "nurse/index";
    }
    @GetMapping("/new") public String createForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("qualificationLevels", NurseQualificationLevel.values());
        return "nurse/form";
    }
    @PostMapping public String create(@Valid @ModelAttribute Nurse nurse, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            model.addAttribute("qualificationLevels", NurseQualificationLevel.values());
            return "nurse/form";
        }
        service.saveNurse(nurse);
        return "redirect:/nurses";
    }
    @GetMapping("/{id}/edit") public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("nurse", service.getNurseById(id).orElseThrow());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("qualificationLevels", NurseQualificationLevel.values());
        return "nurse/form";
    }
    @PostMapping("/{id}") public String update(@PathVariable Long id, @Valid @ModelAttribute Nurse nurse, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            model.addAttribute("qualificationLevels", NurseQualificationLevel.values());
            return "nurse/form";
        }
        nurse.setId(id);
        service.saveNurse(nurse);
        return "redirect:/nurses";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable Long id) {
        service.deleteNurse(id);
        return "redirect:/nurses";
    }
}
