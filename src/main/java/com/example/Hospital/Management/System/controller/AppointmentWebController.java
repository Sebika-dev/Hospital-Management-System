package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Appointment;
import com.example.Hospital.Management.System.model.Department;
import com.example.Hospital.Management.System.model.Patient;
import com.example.Hospital.Management.System.model.Doctor;
import com.example.Hospital.Management.System.model.Nurse;
import com.example.Hospital.Management.System.model.AppointmentStatus;
import com.example.Hospital.Management.System.service.AppointmentService;
import com.example.Hospital.Management.System.service.DepartmentService;
import com.example.Hospital.Management.System.service.PatientService;
import com.example.Hospital.Management.System.service.DoctorService;
import com.example.Hospital.Management.System.service.NurseService;
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
    private final DoctorService doctorService;
    private final NurseService nurseService;

    @Autowired
    public AppointmentWebController(AppointmentService appointmentService,
                                    DepartmentService departmentService,
                                    PatientService patientService,
                                    DoctorService doctorService,
                                    NurseService nurseService) {
        this.appointmentService = appointmentService;
        this.departmentService = departmentService;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.nurseService = nurseService;
    }

    @GetMapping
    public String listAppointments(Model model) {
        var appointments = appointmentService.getAllAppointments();

        Map<String,String> departmentNames = departmentService.getAllDepartments()
                .stream().collect(Collectors.toMap(Department::getId, Department::getName));
        Map<String,String> patientNames = patientService.getAllPatients()
                .stream().collect(Collectors.toMap(Patient::getId, Patient::getName));
        Map<String,String> doctorNames = doctorService.getAllDoctors()
                .stream().collect(Collectors.toMap(Doctor::getId, Doctor::getName));
        Map<String,String> nurseNames = nurseService.getAllNurses()
                .stream().collect(Collectors.toMap(Nurse::getId, Nurse::getName));

        model.addAttribute("appointments", appointments);
        model.addAttribute("departmentNames", departmentNames);
        model.addAttribute("patientNames", patientNames);
        model.addAttribute("doctorNames", doctorNames);
        model.addAttribute("nurseNames", nurseNames);
        return "appointment/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("nurses", nurseService.getAllNurses());
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
