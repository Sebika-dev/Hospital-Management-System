package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.*;
import com.example.Hospital.Management.System.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/appointments")
public class AppointmentWebController {
    private final AppointmentService service;
    private final DepartmentService deptService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final NurseService nurseService;
    private final RoomService roomService;

    public AppointmentWebController(AppointmentService s, DepartmentService d, PatientService p, DoctorService doc, NurseService n, RoomService r) {
        this.service = s; this.deptService = d; this.patientService = p; this.doctorService = doc; this.nurseService = n; this.roomService = r;
    }

    @GetMapping
    public String list(Model model,
                       @RequestParam(required = false) Long patientId,
                       @RequestParam(required = false) Long doctorId,
                       @RequestParam(required = false) AppointmentStatus status,
                       @RequestParam(required = false) LocalDate startDate,
                       @RequestParam(required = false) LocalDate endDate,
                       @RequestParam(defaultValue = "admissionDate") String sortField,
                       @RequestParam(defaultValue = "desc") String sortDir) {

        var appointments = service.getAllAppointments(patientId, doctorId, status, startDate, endDate, sortField, sortDir);

        model.addAttribute("appointments", appointments);
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("statuses", AppointmentStatus.values());

        // Parametrii pentru UI
        model.addAttribute("filterPat", patientId);
        model.addAttribute("filterDoc", doctorId);
        model.addAttribute("filterStat", status);
        model.addAttribute("filterStart", startDate);
        model.addAttribute("filterEnd", endDate);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "appointment/index";
    }

    // ... Metodele vechi (create, edit, delete) ...
    @GetMapping("/new") public String createForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        populateModel(model);
        return "appointment/form";
    }
    @PostMapping public String create(@Valid @ModelAttribute Appointment appointment, BindingResult result, Model model) {
        if (result.hasErrors()) { populateModel(model); return "appointment/form"; }
        service.saveAppointment(appointment);
        return "redirect:/appointments";
    }
    @GetMapping("/{id}/edit") public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", service.getAppointmentById(id).orElseThrow());
        populateModel(model);
        return "appointment/form";
    }
    @PostMapping("/{id}") public String update(@PathVariable Long id, @Valid @ModelAttribute Appointment appointment, BindingResult result, Model model) {
        if (result.hasErrors()) { populateModel(model); return "appointment/form"; }
        appointment.setId(id);
        service.saveAppointment(appointment);
        return "redirect:/appointments";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable Long id) {
        service.deleteAppointment(id);
        return "redirect:/appointments";
    }
    private void populateModel(Model model) {
        model.addAttribute("departments", deptService.getAllDepartments());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("nurses", nurseService.getAllNurses());
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("statuses", AppointmentStatus.values());
    }
}