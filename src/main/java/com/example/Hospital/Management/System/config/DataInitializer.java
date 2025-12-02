package com.example.Hospital.Management.System.config;
import com.example.Hospital.Management.System.model.*;
import com.example.Hospital.Management.System.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final HospitalRepository hRepo;
    private final DepartmentRepository dRepo;
    private final RoomRepository rRepo;
    private final DoctorRepository docRepo;
    private final NurseRepository nRepo;
    private final PatientRepository pRepo;
    private final AppointmentRepository aRepo;
    private final MedicalStaffAppointmentRepository mRepo;

    public DataInitializer(HospitalRepository h, DepartmentRepository d, RoomRepository r, DoctorRepository doc, NurseRepository n, PatientRepository p, AppointmentRepository a, MedicalStaffAppointmentRepository m) {
        this.hRepo = h; this.dRepo = d; this.rRepo = r; this.docRepo = doc; this.nRepo = n; this.pRepo = p; this.aRepo = a; this.mRepo = m;
    }

    @Override
    public void run(String... args) throws Exception {
        if (hRepo.count() > 0) return;

        List<Hospital> hs = new ArrayList<>();
        for (int i = 1; i <= 10; i++) hs.add(new Hospital("Hospital " + i, "City " + i, "Address " + i, "072000000" + i));
        hRepo.saveAll(hs);

        List<Department> ds = new ArrayList<>();
        for (int i = 0; i < 10; i++) ds.add(new Department("Dept " + (i + 1), hs.get(i % 10)));
        dRepo.saveAll(ds);

        List<Room> rs = new ArrayList<>();
        for (int i = 0; i < 10; i++) rs.add(new Room("R" + (i + 1), 4, RoomStatus.AVAILABLE, hs.get(i % 10)));
        rRepo.saveAll(rs);

        List<Doctor> docs = new ArrayList<>();
        for (int i = 0; i < 10; i++) docs.add(new Doctor("Dr. " + i, "L" + i, ds.get(i % 10)));
        docRepo.saveAll(docs);

        List<Nurse> ns = new ArrayList<>();
        for (int i = 0; i < 10; i++) ns.add(new Nurse("Nurse " + i, NurseQualificationLevel.REGISTERED_NURSE, ds.get(i % 10)));
        nRepo.saveAll(ns);

        List<Patient> ps = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Patient p = new Patient("Patient " + i, "07000" + i, hs.get(i % 10));
            p.setDepartment(ds.get(i % 10));
            ps.add(p);
        }
        pRepo.saveAll(ps);

        List<Appointment> as = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Appointment a = new Appointment();
            a.setAdmissionDate(LocalDate.now().plusDays(i));
            a.setStatus(AppointmentStatus.ACTIVE);
            a.setPatient(ps.get(i));
            a.setDepartment(ds.get(i));
            a.setDoctor(docs.get(i));
            a.setNurse(ns.get(i));
            a.setRoom(rs.get(i % 10)); // Set Room on Appointment
            as.add(a);
        }
        aRepo.saveAll(as);

        List<MedicalStaffAppointment> ms = new ArrayList<>();
        for (int i = 0; i < 10; i++) ms.add(new MedicalStaffAppointment(as.get(i).getId(), docs.get(i).getId(), "DOCTOR"));
        mRepo.saveAll(ms);

        System.out.println("Database Initialized.");
    }
}
