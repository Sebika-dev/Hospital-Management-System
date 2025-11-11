package com.example.Hospital.Management.System.service;

import com.example.Hospital.Management.System.model.Appointment;
import com.example.Hospital.Management.System.repository.inmemory.InMemoryAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AppointmentService {
    private final InMemoryAppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final NurseService nurseService;
    private final RoomService roomService;

    @Autowired
    public AppointmentService(InMemoryAppointmentRepository appointmentRepository,
                              PatientService patientService,
                              DoctorService doctorService,
                              NurseService nurseService,
                              RoomService roomService) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.nurseService = nurseService;
        this.roomService = roomService;
    }

    public Appointment addAppointment(Appointment appt) {
        // Dacă lipsește departamentul pe appointment, îl completăm din pacient (dacă există pe pacient)
        if ((appt.getDepartmentId() == null || appt.getDepartmentId().isEmpty())
                && appt.getPatientId() != null) {
            patientService.getPatientById(appt.getPatientId()).ifPresent(p -> {
                if (p.getDepartmentId() != null) {
                    appt.setDepartmentId(p.getDepartmentId());
                }
            });
        }

        Appointment saved = appointmentRepository.save(appt);

        // patient
        if (saved.getPatientId() != null) {
            patientService.getPatientById(saved.getPatientId()).ifPresent(p -> {
                if (!p.getAppointmentIds().contains(saved.getId())) {
                    p.getAppointmentIds().add(saved.getId());
                    patientService.updatePatient(p);
                }
                // room: dacă pacientul e cazat, adaugă appointmentul la camera lui
                if (p.getRoomId() != null) {
                    roomService.getRoomById(p.getRoomId()).ifPresent(r -> {
                        if (!r.getAppointmentIds().contains(saved.getId())) {
                            r.getAppointmentIds().add(saved.getId());
                            roomService.updateRoom(r);
                        }
                    });
                }
            });
        }

        // doctor
        if (saved.getDoctorId() != null && !saved.getDoctorId().isEmpty()) {
            doctorService.getDoctorById(saved.getDoctorId()).ifPresent(d -> {
                if (!d.getAppointmentIds().contains(saved.getId())) {
                    d.getAppointmentIds().add(saved.getId());
                    doctorService.updateDoctor(d);
                }
            });
        }

        // nurse
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
        appointmentRepository.findById(appt.getId()).ifPresent(old -> {
            // doctor change
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

            // nurse change
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
            // patient
            if (appt.getPatientId() != null) {
                patientService.getPatientById(appt.getPatientId()).ifPresent(p -> {
                    p.getAppointmentIds().remove(id);
                    patientService.updatePatient(p);

                    if (p.getRoomId() != null) {
                        roomService.getRoomById(p.getRoomId()).ifPresent(r -> {
                            r.getAppointmentIds().remove(id);
                            roomService.updateRoom(r);
                        });
                    }
                });
            }
            // doctor
            if (appt.getDoctorId() != null && !appt.getDoctorId().isEmpty()) {
                doctorService.getDoctorById(appt.getDoctorId()).ifPresent(d -> {
                    d.getAppointmentIds().remove(id);
                    doctorService.updateDoctor(d);
                });
            }
            // nurse
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
