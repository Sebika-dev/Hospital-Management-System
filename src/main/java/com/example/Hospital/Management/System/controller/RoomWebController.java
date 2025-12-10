package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Room;
import com.example.Hospital.Management.System.model.RoomStatus;
import com.example.Hospital.Management.System.service.HospitalService;
import com.example.Hospital.Management.System.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomWebController {
    private final RoomService service;
    private final HospitalService hospitalService;
    @Autowired public RoomWebController(RoomService service, HospitalService hospitalService) {
        this.service = service; this.hospitalService = hospitalService;
    }

    @GetMapping
    public String list(Model model,
                       @RequestParam(required = false) String number,
                       @RequestParam(required = false) Long hospitalId,
                       @RequestParam(required = false) RoomStatus status,
                       @RequestParam(required = false) Integer minCapacity,
                       @RequestParam(defaultValue = "number") String sortField,
                       @RequestParam(defaultValue = "asc") String sortDir) {

        var rooms = service.getAllRooms(number, hospitalId, status, minCapacity, sortField, sortDir);

        model.addAttribute("rooms", rooms);
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("statuses", RoomStatus.values());

        model.addAttribute("filterNumber", number);
        model.addAttribute("filterHos", hospitalId);
        model.addAttribute("filterStatus", status);
        model.addAttribute("filterCap", minCapacity);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "room/index";
    }

    // ... Metode standard ...
    @GetMapping("/new") public String createForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("statuses", RoomStatus.values());
        return "room/form";
    }
    @PostMapping public String create(@Valid @ModelAttribute Room room, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("hospitals", hospitalService.getAllHospitals());
            model.addAttribute("statuses", RoomStatus.values());
            return "room/form";
        }
        service.saveRoom(room);
        return "redirect:/rooms";
    }
    @GetMapping("/{id}/edit") public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("room", service.getRoomById(id).orElseThrow());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("statuses", RoomStatus.values());
        return "room/form";
    }
    @PostMapping("/{id}") public String update(@PathVariable Long id, @Valid @ModelAttribute Room room, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("hospitals", hospitalService.getAllHospitals());
            model.addAttribute("statuses", RoomStatus.values());
            return "room/form";
        }
        room.setId(id);
        service.saveRoom(room);
        return "redirect:/rooms";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable Long id) {
        service.deleteRoom(id);
        return "redirect:/rooms";
    }
}