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
        // Auto-fill department din pacient dacă lipsește
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

        // Update Patient
        if (saved.getPatientId() != null) {
            patientService.getPatientById(saved.getPatientId()).ifPresent(p -> {
                if (!p.getAppointmentIds().contains(saved.getId())) {
                    p.getAppointmentIds().add(saved.getId());
                    // Folosim save direct din repository-ul pacientului pentru a evita validarea duplicat aici
                    // sau folosim updatePatient dacă suntem siguri că datele pacientului sunt valide
                    patientService.updatePatient(p);
                }
                // Update Room (prin pacient)
                if (p.getRoomId() != null) {
                    roomService.attachAppointmentToRoom(p.getRoomId(), saved.getId());
                }
            });
        }

        // Update Doctor
        if (saved.getDoctorId() != null && !saved.getDoctorId().isEmpty()) {
            doctorService.getDoctorById(saved.getDoctorId()).ifPresent(d -> {
                if (!d.getAppointmentIds().contains(saved.getId())) {
                    d.getAppointmentIds().add(saved.getId());
                    doctorService.updateDoctor(d);
                }
            });
        }

        // Update Nurse
        if (saved.getNurseId() != null && !saved.getNurseId().isEmpty()) {
            nurseService.getNurseById(saved.getNurseId()).ifPresent(n -> {
                if (!n.getAppointmentIds().contains(saved.getId())) {
                    n.getAppointmentIds().add(saved.getId());
                    nurseService.updateNurse(n);
                }
            });
        }

        return saved;
    }

    public Appointment updateAppointment(Appointment appt) {
        validator.validateAppointment(appt);

        appointmentRepository.findById(appt.getId()).ifPresent(old -> {
            // Gestionare schimbare Doctor
            if (!Objects.equals(old.getDoctorId(), appt.getDoctorId())) {
                if (old.getDoctorId() != null && !old.getDoctorId().isEmpty()) {
                    doctorService.getDoctorById(old.getDoctorId()).ifPresent(d -> {
                        d.getAppointmentIds().remove(old.getId());
                        doctorService.updateDoctor(d);
                    });
                }
                if (appt.getDoctorId() != null && !appt.getDoctorId().isEmpty()) {
                    doctorService.getDoctorById(appt.getDoctorId()).ifPresent(d -> {
                        if (!d.getAppointmentIds().contains(appt.getId())) {
                            d.getAppointmentIds().add(appt.getId());
                            doctorService.updateDoctor(d);
                        }
                    });
                }
            }

            // Gestionare schimbare Nurse
            if (!Objects.equals(old.getNurseId(), appt.getNurseId())) {
                if (old.getNurseId() != null && !old.getNurseId().isEmpty()) {
                    nurseService.getNurseById(old.getNurseId()).ifPresent(n -> {
                        n.getAppointmentIds().remove(old.getId());
                        nurseService.updateNurse(n);
                    });
                }
                if (appt.getNurseId() != null && !appt.getNurseId().isEmpty()) {
                    nurseService.getNurseById(appt.getNurseId()).ifPresent(n -> {
                        if (!n.getAppointmentIds().contains(appt.getId())) {
                            n.getAppointmentIds().add(appt.getId());
                            nurseService.updateNurse(n);
                        }
                    });
                }
            }
        });
        return appointmentRepository.save(appt);
    }

    public Optional<Appointment> getAppointmentById(String id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void deleteAppointment(String id) {
        appointmentRepository.findById(id).ifPresent(appt -> {
            // Curățare referințe din Patient și Room
            if (appt.getPatientId() != null) {
                patientService.getPatientById(appt.getPatientId()).ifPresent(p -> {
                    p.getAppointmentIds().remove(id);
                    patientService.updatePatient(p);

                    if (p.getRoomId() != null) {
                        roomService.detachAppointmentFromRoom(p.getRoomId(), id);
                    }
                });
            }
            // Curățare referințe Doctor
            if (appt.getDoctorId() != null && !appt.getDoctorId().isEmpty()) {
                doctorService.getDoctorById(appt.getDoctorId()).ifPresent(d -> {
                    d.getAppointmentIds().remove(id);
                    doctorService.updateDoctor(d);
                });
            }
            // Curățare referințe Nurse
            if (appt.getNurseId() != null && !appt.getNurseId().isEmpty()) {
                nurseService.getNurseById(appt.getNurseId()).ifPresent(n -> {
                    n.getAppointmentIds().remove(id);
                    nurseService.updateNurse(n);
                });
            }
        });
        appointmentRepository.delete(id);
    }
}