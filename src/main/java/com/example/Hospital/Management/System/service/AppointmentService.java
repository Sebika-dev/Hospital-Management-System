package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Appointment;
import com.example.Hospital.Management.System.repository.infile.FileAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AppointmentService {
    private final FileAppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final NurseService nurseService;
    private final RoomService roomService;
    private final Validator validator;

    @Autowired
    public AppointmentService(FileAppointmentRepository appointmentRepository,
                              PatientService patientService,
                              DoctorService doctorService,
                              NurseService nurseService,
                              RoomService roomService,
                              Validator validator) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.nurseService = nurseService;
        this.roomService = roomService;
        this.validator = validator;
    }

    public Appointment addAppointment(Appointment appt) {
        if ((appt.getDepartmentId() == null || appt.getDepartmentId().isEmpty())
                && appt.getPatientId() != null) {
            patientService.getPatientById(appt.getPatientId()).ifPresent(p -> {
                if (p.getDepartmentId() != null) {
                    appt.setDepartmentId(p.getDepartmentId());
                }
            });
        }

        validator.validateAppointment(appt);

        Appointment saved = appointmentRepository.save(appt);

        // Update Patient și Room
        if (saved.getPatientId() != null) {
            // 1. Găsim camera pacientului (dacă are)
            String roomId = patientService.getPatientById(saved.getPatientId())
                    .map(p -> p.getRoomId())
                    .orElse(null);

            // 2. Actualizăm pacientul (folosind metoda sigură care nu blochează la validare)
            patientService.addAppointmentToPatient(saved.getPatientId(), saved.getId());

            // 3. Actualizăm camera
            if (roomId != null) {
                roomService.attachAppointmentToRoom(roomId, saved.getId());
            }
        }

        // Update Doctor
        if (saved.getDoctorId() != null && !saved.getDoctorId().isEmpty()) {
            doctorService.addAppointmentToDoctor(saved.getDoctorId(), saved.getId());
        }

        // Update Nurse
        if (saved.getNurseId() != null && !saved.getNurseId().isEmpty()) {
            nurseService.addAppointmentToNurse(saved.getNurseId(), saved.getId());
        }

        return saved;
    }

    public Appointment updateAppointment(Appointment appt) {
        validator.validateAppointment(appt);

        appointmentRepository.findById(appt.getId()).ifPresent(old -> {
            // Doctor change logic
            if (!Objects.equals(old.getDoctorId(), appt.getDoctorId())) {
                // remove from old
                if (old.getDoctorId() != null) doctorService.getDoctorById(old.getDoctorId()).ifPresent(d -> {
                    d.getAppointmentIds().remove(old.getId());
                    // Hack: save directly to avoid full re-validation of old doctor
                    // doctorService.updateDoctor(d); -> ar putea valida
                    // Ideal ar fi o metodă removeAppointmentFromDoctor, dar update e ok de obicei
                });
                // add to new
                if (appt.getDoctorId() != null) {
                    doctorService.addAppointmentToDoctor(appt.getDoctorId(), appt.getId());
                }
            }
            // Nurse change logic (similar)
            // ...
        });
        return appointmentRepository.save(appt);
    }

    // Restul metodelor (get, delete, etc.) rămân la fel...
    public Optional<Appointment> getAppointmentById(String id) { return appointmentRepository.findById(id); }
    public List<Appointment> getAllAppointments() { return appointmentRepository.findAll(); }
    public void deleteAppointment(String id) {
        appointmentRepository.findById(id).ifPresent(appt -> {
            if (appt.getPatientId() != null) {
                patientService.removeAppointmentFromPatient(appt.getPatientId(), id);
                patientService.getPatientById(appt.getPatientId()).ifPresent(p -> {
                    if (p.getRoomId() != null) roomService.detachAppointmentFromRoom(p.getRoomId(), id);
                });
            }
            // Cleanup doctor/nurse refs...
        });
        appointmentRepository.delete(id);
    }
}