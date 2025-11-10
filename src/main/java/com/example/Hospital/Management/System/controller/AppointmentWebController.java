package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Appointment;
import com.example.Hospital.Management.System.model.Department;
import com.example.Hospital.Management.System.model.Patient;
import com.example.Hospital.Management.System.model.AppointmentStatus;
import com.example.Hospital.Management.System.service.AppointmentService;
import com.example.Hospital.Management.System.service.DepartmentService;
import com.example.Hospital.Management.System.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/appointments")
public class AppointmentWebController {
    private final AppointmentService appointmentService;
    private final DepartmentService departmentService;
    private final PatientService patientService;

    @Autowired
    public AppointmentWebController(AppointmentService appointmentService,
                                    DepartmentService departmentService,
                                    PatientService patientService) {
        this.appointmentService = appointmentService;
        this.departmentService = departmentService;
        this.patientService = patientService;
    }

    @GetMapping
    public String listAppointments(Model model) {
        var appointments = appointmentService.getAllAppointments();

        Map<String,String> departmentNames = departmentService.getAllDepartments()
                .stream().collect(Collectors.toMap(Department::getId, Department::getName));
        Map<String,String> patientNames = patientService.getAllPatients()
                .stream().collect(Collectors.toMap(Patient::getId, Patient::getName));

        model.addAttribute("appointments", appointments);
        model.addAttribute("departmentNames", departmentNames);
        model.addAttribute("patientNames", patientNames);
        return "appointment/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("statuses", AppointmentStatus.values());
        return "appointment/form";
    }

    @PostMapping
    public String createAppointment(@ModelAttribute Appointment appointment) {
        appointmentService.addAppointment(appointment);
        return "redirect:/appointments";
    }

    @PostMapping("/{id}/delete")
    public String deleteAppointment(@PathVariable String id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }
}
