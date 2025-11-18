package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Department;
import com.example.Hospital.Management.System.model.Nurse;
import com.example.Hospital.Management.System.model.NurseQualificationLevel;
import com.example.Hospital.Management.System.service.DepartmentService;
import com.example.Hospital.Management.System.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/nurses")
public class NurseWebController {
    private final NurseService nurseService;
    private final DepartmentService departmentService;

    @Autowired
    public NurseWebController(NurseService nurseService,
                              DepartmentService departmentService) {
        this.nurseService = nurseService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String listNurses(Model model) {
        var nurses = nurseService.getAllNurses();
        Map<String,String> departmentNames = departmentService.getAllDepartments()
                .stream().collect(Collectors.toMap(Department::getId, Department::getName));
        model.addAttribute("nurses", nurses);
        model.addAttribute("departmentNames", departmentNames);
        return "nurse/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("qualificationLevels", NurseQualificationLevel.values());
        return "nurse/form";
    }

    @PostMapping
    public String createNurse(@ModelAttribute Nurse nurse) {
        nurseService.addNurse(nurse);
        return "redirect:/nurses";
    }

    @PostMapping("/{id}/delete")
    public String deleteNurse(@PathVariable String id) {
        nurseService.deleteNurse(id);
        return "redirect:/nurses";
    }

    @GetMapping("/{id}/edit")
    public String editNurse(@PathVariable String id, Model model) {
        Nurse nurse = nurseService.getNurseById(id).orElseThrow();
        model.addAttribute("nurse", nurse);
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("qualificationLevels", NurseQualificationLevel.values());
        return "nurse/form";
    }

    @PostMapping("/{id}")
    public String updateNurse(@PathVariable String id, @ModelAttribute Nurse nurse) {
        nurse.setId(id);
        nurseService.updateNurse(nurse);
        return "redirect:/nurses";
    }
}
