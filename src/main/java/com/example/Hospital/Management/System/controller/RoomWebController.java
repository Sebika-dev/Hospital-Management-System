package com.example.Hospital.Management.System.controller;

import com.example.Hospital.Management.System.model.Hospital;
import com.example.Hospital.Management.System.model.Room;
import com.example.Hospital.Management.System.model.RoomStatus;
import com.example.Hospital.Management.System.service.HospitalService;
import com.example.Hospital.Management.System.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rooms")
public class RoomWebController {
    private final RoomService roomService;
    private final HospitalService hospitalService;

    @Autowired
    public RoomWebController(RoomService roomService,
                             HospitalService hospitalService) {
        this.roomService = roomService;
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String listRooms(Model model) {
        var rooms = roomService.getAllRooms();
        Map<String,String> hospitalNames = hospitalService.getAllHospitals()
                .stream().collect(Collectors.toMap(Hospital::getId, Hospital::getName));
        model.addAttribute("rooms", rooms);
        model.addAttribute("hospitalNames", hospitalNames);
        return "room/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("statuses", RoomStatus.values());
        return "room/form";
    }

    @PostMapping
    public String createRoom(@ModelAttribute Room room, Model model) {
        try {
            roomService.addRoom(room);
            return "redirect:/rooms";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("hospitals", hospitalService.getAllHospitals());
            model.addAttribute("statuses", RoomStatus.values());
            return "room/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editRoom(@PathVariable String id, Model model) {
        model.addAttribute("room", roomService.getRoomById(id).orElseThrow());
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        model.addAttribute("statuses", RoomStatus.values());
        return "room/form";
    }

    @PostMapping("/{id}")
    public String updateRoom(@PathVariable String id, @ModelAttribute Room room, Model model) {
        try {
            room.setId(id);
            roomService.updateRoom(room);
            return "redirect:/rooms";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("hospitals", hospitalService.getAllHospitals());
            model.addAttribute("statuses", RoomStatus.values());
            return "room/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }
}