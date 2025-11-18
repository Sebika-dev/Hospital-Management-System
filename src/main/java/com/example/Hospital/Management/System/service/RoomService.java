package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Room;
import com.example.Hospital.Management.System.repository.infile.FileRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final FileRoomRepository roomRepository;
    private final HospitalService hospitalService;
    private final Validator validator;

    @Autowired
    public RoomService(FileRoomRepository roomRepository,
                       @Lazy HospitalService hospitalService,
                       Validator validator) {
        this.roomRepository = roomRepository;
        this.hospitalService = hospitalService;
        this.validator = validator;
    }

    public Room addRoom(Room room) {
        validator.validateRoom(room);

        Room saved = roomRepository.save(room);
        if (saved.getHospitalId() != null) {
            hospitalService.getHospitalById(saved.getHospitalId()).ifPresent(h -> {
                if (!h.getRoomIds().contains(saved.getId())) {
                    h.addRoom(saved.getId());
                    hospitalService.updateHospital(h);
                }
            });
        }
        return saved;
    }

    public Room updateRoom(Room room) {
        validator.validateRoom(room);

        roomRepository.findById(room.getId()).ifPresent(old -> {
            String oldHospital = old.getHospitalId();
            String newHospital = room.getHospitalId();

            if (oldHospital != null && !oldHospital.equals(newHospital)) {
                hospitalService.getHospitalById(oldHospital).ifPresent(h -> {
                    h.getRoomIds().remove(room.getId());
                    hospitalService.updateHospital(h);
                });
            }
            if (newHospital != null && !newHospital.equals(oldHospital)) {
                hospitalService.getHospitalById(newHospital).ifPresent(h -> {
                    if (!h.getRoomIds().contains(room.getId())) {
                        h.addRoom(room.getId());
                        hospitalService.updateHospital(h);
                    }
                });
            }
        });

        return roomRepository.save(room);
    }

    public Optional<Room> getRoomById(String id) {
        return roomRepository.findById(id);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> getRoomsByHospitalId(String hospitalId) {
        return roomRepository.findByHospitalId(hospitalId);
    }

    public void deleteRoom(String id) {
        roomRepository.findById(id).ifPresent(room -> {
            if (room.getHospitalId() != null) {
                hospitalService.getHospitalById(room.getHospitalId()).ifPresent(h -> {
                    h.getRoomIds().remove(id);
                    hospitalService.updateHospital(h);
                });
            }
        });
        roomRepository.delete(id);
    }

    public void deleteAllRooms() {
        roomRepository.deleteAll();
    }

    public void attachAppointmentToRoom(String roomId, String appointmentId) {
        if (roomId == null || appointmentId == null) return;
        getRoomById(roomId).ifPresent(r -> {
            if (!r.getAppointmentIds().contains(appointmentId)) {
                r.getAppointmentIds().add(appointmentId);
                // Salvare directă fără validare completă (e doar o actualizare de relație)
                roomRepository.save(r);
            }
        });
    }

    public void detachAppointmentFromRoom(String roomId, String appointmentId) {
        if (roomId == null || appointmentId == null) return;
        getRoomById(roomId).ifPresent(r -> {
            if (r.getAppointmentIds().remove(appointmentId)) {
                roomRepository.save(r);
            }
        });
    }
}