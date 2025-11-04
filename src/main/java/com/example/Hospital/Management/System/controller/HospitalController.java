package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Hospital;
import com.example.Hospital.Management.System.model.Department;
import com.example.Hospital.Management.System.model.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HospitalController {

    // Listă statică pentru stocarea temporară a spitalelor
    private static List<Hospital> hospitals = new ArrayList<>();

    // Afișează lista de spitale
    @GetMapping("/hospitals")
    public String getAllHospitals(Model model) {
        model.addAttribute("hospitals", hospitals); // Trimite lista existentă
        return "hospital/index"; // Calea către index.html
    }

    // Afișează formularul pentru adăugare
    @GetMapping("/hospitals/new")
    public String showHospitalForm(Model model) {
        Hospital hospital = new Hospital();
        hospital.getDepartments().add(new Department()); // Inițializează cu un departament gol
        hospital.getRooms().add(new Room()); // Inițializează cu o cameră goală
        model.addAttribute("hospital", hospital);
        return "hospital/form"; // Calea către form.html
    }

    // Procesează salvarea spitalului
    @PostMapping("/hospitals")
    public String createHospital(@ModelAttribute Hospital hospital) {
        hospitals.add(hospital); // Adaugă spitalul în listă
        System.out.println("Salvat spital: " + hospital.getName());
        return "redirect:/hospitals"; // Redirect către lista de spitale
    }
}