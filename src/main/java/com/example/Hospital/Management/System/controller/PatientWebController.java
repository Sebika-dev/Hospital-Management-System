package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Patient;
import com.example.Hospital.Management.System.service.PatientService;
import com.example.Hospital.Management.System.service.RoomService;
import com.example.Hospital.Management.System.service.DepartmentService;
import com.example.Hospital.Management.System.model.Room;
import com.example.Hospital.Management.System.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/patients")
public class PatientWebController {
    private final PatientService patientService;
    private final RoomService roomService;
    private final DepartmentService departmentService;

    @Autowired
    public PatientWebController(PatientService patientService,
                                RoomService roomService,
                                DepartmentService departmentService) {
        this.patientService = patientService;
        this.roomService = roomService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());

        // map-uri pentru UI (opțional să afișezi numele camerei/departmentului)
        Map<String,String> roomLabels = roomService.getAllRooms()
                .stream().collect(Collectors.toMap(Room::getId, r -> "Room " + r.getNumber()));
        Map<String,String> departmentNames = departmentService.getAllDepartments()
                .stream().collect(Collectors.toMap(Department::getId, Department::getName));
        model.addAttribute("roomLabels", roomLabels);
        model.addAttribute("departmentNames", departmentNames);

        return "patient/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "patient/form";
    }

    @PostMapping
    public String createPatient(@ModelAttribute Patient patient) {
        patientService.addPatient(patient);
        return "redirect:/patients";
    }

    // opțional: edit
    @GetMapping("/{id}/edit")
    public String editPatient(@PathVariable String id, Model model) {
        var p = patientService.getPatientById(id).orElseThrow();
        model.addAttribute("patient", p);
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "patient/form";
    }

    @PostMapping("/{id}")
    public String updatePatient(@PathVariable String id, @ModelAttribute Patient patient) {
        patient.setId(id);
        patientService.updatePatient(patient); // va sincroniza camera (Room.appointmentIds) conform logicii din service
        return "redirect:/patients";
    }

    @PostMapping("/{id}/delete")
    public String deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
