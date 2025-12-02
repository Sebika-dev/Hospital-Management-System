package com.example.Hospital.Management.System.config;

import com.example.Hospital.Management.System.model.*;
import com.example.Hospital.Management.System.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer2 implements CommandLineRunner {

    private final HospitalRepository hRepo;
    private final DepartmentRepository dRepo;
    private final RoomRepository rRepo;
    private final DoctorRepository docRepo;
    private final NurseRepository nRepo;
    private final PatientRepository pRepo;
    private final AppointmentRepository aRepo;
    private final MedicalStaffAppointmentRepository mRepo;

    public DataInitializer2(HospitalRepository h, DepartmentRepository d, RoomRepository r, DoctorRepository doc, NurseRepository n, PatientRepository p, AppointmentRepository a, MedicalStaffAppointmentRepository m) {
        this.hRepo = h;
        this.dRepo = d;
        this.rRepo = r;
        this.docRepo = doc;
        this.nRepo = n;
        this.pRepo = p;
        this.aRepo = a;
        this.mRepo = m;
    }

    @Override
    public void run(String... args) throws Exception {
        // Evitam duplicarea datelor la restart
        if (hRepo.count() > 0) return;

        System.out.println("Se initializeaza baza de date cu 10 intrari realiste (fara diacritice)...");

        // ---------------------------------------------------------------------
        // 1. SPITALE (10)
        // ---------------------------------------------------------------------
        Hospital h1 = new Hospital("Spitalul Judetean de Urgenta Cluj", "Cluj-Napoca", "Str. Clinicilor 3", "0264597852");
        Hospital h2 = new Hospital("Spitalul Militar Central", "Bucuresti", "Calea Plevnei 134", "0213193051");
        Hospital h3 = new Hospital("Spitalul Clinic Judetean Timisoara", "Timisoara", "Bd. Liviu Rebreanu 156", "0256462888");
        Hospital h4 = new Hospital("Spitalul Sfantul Spiridon", "Iasi", "Bd. Independentei 1", "0232240822");
        Hospital h5 = new Hospital("Spitalul Judetean Constanta", "Constanta", "Bd. Tomis 145", "0241662222");
        Hospital h6 = new Hospital("Spitalul Clinic de Urgenta Floreasca", "Bucuresti", "Calea Floreasca 8", "0215992300");
        Hospital h7 = new Hospital("Institutul Inimii", "Cluj-Napoca", "Str. Motilor 19", "0264591941");
        Hospital h8 = new Hospital("Spitalul de Copii Grigore Alexandrescu", "Bucuresti", "Bd. Iancu de Hunedoara 30", "0213169366");
        Hospital h9 = new Hospital("Spitalul Universitar", "Bucuresti", "Splaiul Independentei 169", "0213180522");
        Hospital h10 = new Hospital("Spitalul Municipal Oradea", "Oradea", "Str. Corneliu Coposu 12", "0259437750");

        List<Hospital> hospitals = Arrays.asList(h1, h2, h3, h4, h5, h6, h7, h8, h9, h10);
        hRepo.saveAll(hospitals);

        // ---------------------------------------------------------------------
        // 2. DEPARTAMENTE (10) - Legate logic de spitale
        // ---------------------------------------------------------------------
        Department d1 = new Department("Cardiologie", h7); // Inst. Inimii
        d1.setHeadDoctorName("Dr. Popescu Ion"); d1.setPhoneNumber("0264111222");

        Department d2 = new Department("Neurologie", h1); // Cluj
        d2.setHeadDoctorName("Dr. Ionescu Maria"); d2.setPhoneNumber("0264222333");

        Department d3 = new Department("Chirurgie Generala", h6); // Floreasca
        d3.setHeadDoctorName("Dr. Marinescu George"); d3.setPhoneNumber("021333444");

        Department d4 = new Department("Ortopedie", h2); // Militar
        d4.setHeadDoctorName("Dr. Radu Vasile"); d4.setPhoneNumber("021444555");

        Department d5 = new Department("Pediatrie", h8); // Grigore Alexandrescu
        d5.setHeadDoctorName("Dr. Dumitrescu Ana"); d5.setPhoneNumber("021555666");

        Department d6 = new Department("ATI", h3); // Timisoara
        d6.setHeadDoctorName("Dr. Stan Adrian"); d6.setPhoneNumber("0256777888");

        Department d7 = new Department("Oncologie", h9); // Universitar
        d7.setHeadDoctorName("Dr. Gheorghe Elena"); d7.setPhoneNumber("021888999");

        Department d8 = new Department("Ginecologie", h5); // Constanta
        d8.setHeadDoctorName("Dr. Moldovan Carmen"); d8.setPhoneNumber("0241000111");

        Department d9 = new Department("Dermatologie", h4); // Iasi
        d9.setHeadDoctorName("Dr. Stoica Mihai"); d9.setPhoneNumber("0232222333");

        Department d10 = new Department("Urologie", h10); // Oradea
        d10.setHeadDoctorName("Dr. Crisan Alexandru"); d10.setPhoneNumber("0259333444");

        List<Department> departments = Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10);
        dRepo.saveAll(departments);

        // ---------------------------------------------------------------------
        // 3. CAMERE (10) - Legate de spitalele departamentelor de mai sus
        // ---------------------------------------------------------------------
        Room r1 = new Room("101", 2, RoomStatus.OCCUPIED, h7); // pt Cardiologie
        Room r2 = new Room("205", 3, RoomStatus.AVAILABLE, h1); // pt Neuro
        Room r3 = new Room("Sal. 3", 4, RoomStatus.OCCUPIED, h6); // pt Chirurgie
        Room r4 = new Room("Rezerva 1", 1, RoomStatus.AVAILABLE, h2); // pt Orto
        Room r5 = new Room("Copii 10", 2, RoomStatus.OCCUPIED, h8); // pt Pediatrie
        Room r6 = new Room("ATI 1", 1, RoomStatus.OCCUPIED, h3); // pt ATI
        Room r7 = new Room("304", 2, RoomStatus.AVAILABLE, h9); // pt Onco
        Room r8 = new Room("401", 3, RoomStatus.OCCUPIED, h5); // pt Gineco
        Room r9 = new Room("12", 2, RoomStatus.AVAILABLE, h4); // pt Derma
        Room r10 = new Room("Uro 2", 2, RoomStatus.OCCUPIED, h10); // pt Uro

        List<Room> rooms = Arrays.asList(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
        rRepo.saveAll(rooms);

        // ---------------------------------------------------------------------
        // 4. MEDICI (10) - Legati de departamente
        // ---------------------------------------------------------------------
        Doctor doc1 = new Doctor("Dr. Popescu Ion", "RO-10001", d1); // Cardio
        Doctor doc2 = new Doctor("Dr. Ionescu Maria", "RO-20002", d2); // Neuro
        Doctor doc3 = new Doctor("Dr. Marinescu George", "RO-30003", d3); // Chirurgie
        Doctor doc4 = new Doctor("Dr. Radu Vasile", "RO-40004", d4); // Orto
        Doctor doc5 = new Doctor("Dr. Dumitrescu Ana", "RO-50005", d5); // Pediatrie
        Doctor doc6 = new Doctor("Dr. Stan Adrian", "RO-60006", d6); // ATI
        Doctor doc7 = new Doctor("Dr. Gheorghe Elena", "RO-70007", d7); // Onco
        Doctor doc8 = new Doctor("Dr. Moldovan Carmen", "RO-80008", d8); // Gineco
        Doctor doc9 = new Doctor("Dr. Stoica Mihai", "RO-90009", d9); // Derma
        Doctor doc10 = new Doctor("Dr. Crisan Alexandru", "RO-10101", d10); // Uro

        List<Doctor> doctors = Arrays.asList(doc1, doc2, doc3, doc4, doc5, doc6, doc7, doc8, doc9, doc10);
        docRepo.saveAll(doctors);

        // ---------------------------------------------------------------------
        // 5. ASISTENTE (10)
        // ---------------------------------------------------------------------
        Nurse n1 = new Nurse("Asist. Albu Cristina", NurseQualificationLevel.REGISTERED_NURSE, d1);
        Nurse n2 = new Nurse("Asist. Negru Andreea", NurseQualificationLevel.PRACTICAL_NURSE, d2);
        Nurse n3 = new Nurse("Asist. Rosu Mihaela", NurseQualificationLevel.REGISTERED_NURSE, d3);
        Nurse n4 = new Nurse("Asist. Verde Larisa", NurseQualificationLevel.PRACTICAL_NURSE, d4);
        Nurse n5 = new Nurse("Asist. Muntean Diana", NurseQualificationLevel.REGISTERED_NURSE, d5);
        Nurse n6 = new Nurse("Asist. Dragomir Oana", NurseQualificationLevel.REGISTERED_NURSE, d6);
        Nurse n7 = new Nurse("Asist. Luca Simona", NurseQualificationLevel.PRACTICAL_NURSE, d7);
        Nurse n8 = new Nurse("Asist. Matei Gabriela", NurseQualificationLevel.REGISTERED_NURSE, d8);
        Nurse n9 = new Nurse("Asist. Nistor Raluca", NurseQualificationLevel.PRACTICAL_NURSE, d9);
        Nurse n10 = new Nurse("Asist. Florea Camelia", NurseQualificationLevel.REGISTERED_NURSE, d10);

        List<Nurse> nurses = Arrays.asList(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10);
        nRepo.saveAll(nurses);

        // ---------------------------------------------------------------------
        // 6. PACIENTI (10)
        // ---------------------------------------------------------------------
        Patient p1 = new Patient("Popa Andrei", "0722111111", h7); p1.setDepartment(d1); // Cardio
        Patient p2 = new Patient("Rus Vasile", "0722222222", h1); p2.setDepartment(d2); // Neuro
        Patient p3 = new Patient("Dan Ionut", "0722333333", h6); p3.setDepartment(d3); // Chirurgie
        Patient p4 = new Patient("Toma Alexandru", "0722444444", h2); p4.setDepartment(d4); // Orto
        Patient p5 = new Patient("Micul Luca", "0722555555", h8); p5.setDepartment(d5); // Pediatrie
        Patient p6 = new Patient("Lazar Florin", "0722666666", h3); p6.setDepartment(d6); // ATI
        Patient p7 = new Patient("Costea Mirela", "0722777777", h9); p7.setDepartment(d7); // Onco
        Patient p8 = new Patient("Badea Ioana", "0722888888", h5); p8.setDepartment(d8); // Gineco
        Patient p9 = new Patient("Dobre Cristian", "0722999999", h4); p9.setDepartment(d9); // Derma
        Patient p10 = new Patient("Voicu Adrian", "0722000000", h10); p10.setDepartment(d10); // Uro

        List<Patient> patients = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
        pRepo.saveAll(patients);

        // ---------------------------------------------------------------------
        // 7. PROGRAMARI (10) - Legatura vitala: Patient + Room + Staff
        // ---------------------------------------------------------------------
        List<Appointment> apps = new ArrayList<>();

        // 1. Cardio: Popa Andrei, Camera 101, Dr. Popescu, Asist. Albu
        Appointment a1 = new Appointment();
        a1.setAdmissionDate(LocalDate.now().plusDays(2)); a1.setStatus(AppointmentStatus.ACTIVE);
        a1.setPatient(p1); a1.setRoom(r1); a1.setDepartment(d1); a1.setDoctor(doc1); a1.setNurse(n1);
        apps.add(a1);

        // 2. Neuro: Rus Vasile, Camera 205, Dr. Ionescu, Asist. Negru
        Appointment a2 = new Appointment();
        a2.setAdmissionDate(LocalDate.now()); a2.setStatus(AppointmentStatus.ACTIVE);
        a2.setPatient(p2); a2.setRoom(r2); a2.setDepartment(d2); a2.setDoctor(doc2); a2.setNurse(n2);
        apps.add(a2);

        // 3. Chirurgie: Dan Ionut, Camera Sal. 3, Dr. Marinescu, Asist. Rosu
        Appointment a3 = new Appointment();
        a3.setAdmissionDate(LocalDate.now().minusDays(1)); a3.setStatus(AppointmentStatus.ACTIVE);
        a3.setPatient(p3); a3.setRoom(r3); a3.setDepartment(d3); a3.setDoctor(doc3); a3.setNurse(n3);
        apps.add(a3);

        // 4. Orto: Toma Alexandru, Camera Rezerva 1, Dr. Radu, Asist. Verde
        Appointment a4 = new Appointment();
        a4.setAdmissionDate(LocalDate.now().plusDays(5)); a4.setStatus(AppointmentStatus.ACTIVE);
        a4.setPatient(p4); a4.setRoom(r4); a4.setDepartment(d4); a4.setDoctor(doc4); a4.setNurse(n4);
        apps.add(a4);

        // 5. Pediatrie: Micul Luca, Camera Copii 10, Dr. Dumitrescu, Asist. Muntean
        Appointment a5 = new Appointment();
        a5.setAdmissionDate(LocalDate.now().minusDays(2)); a5.setStatus(AppointmentStatus.ACTIVE);
        a5.setPatient(p5); a5.setRoom(r5); a5.setDepartment(d5); a5.setDoctor(doc5); a5.setNurse(n5);
        apps.add(a5);

        // 6. ATI: Lazar Florin, Camera ATI 1, Dr. Stan, Asist. Dragomir
        Appointment a6 = new Appointment();
        a6.setAdmissionDate(LocalDate.now()); a6.setStatus(AppointmentStatus.ACTIVE);
        a6.setPatient(p6); a6.setRoom(r6); a6.setDepartment(d6); a6.setDoctor(doc6); a6.setNurse(n6);
        apps.add(a6);

        // 7. Onco: Costea Mirela, Camera 304, Dr. Gheorghe, Asist. Luca
        Appointment a7 = new Appointment();
        a7.setAdmissionDate(LocalDate.now().minusDays(10)); a7.setStatus(AppointmentStatus.COMPLETED);
        a7.setPatient(p7); a7.setRoom(r7); a7.setDepartment(d7); a7.setDoctor(doc7); a7.setNurse(n7);
        apps.add(a7);

        // 8. Gineco: Badea Ioana, Camera 401, Dr. Moldovan, Asist. Matei
        Appointment a8 = new Appointment();
        a8.setAdmissionDate(LocalDate.now().plusDays(10)); a8.setStatus(AppointmentStatus.ACTIVE);
        a8.setPatient(p8); a8.setRoom(r8); a8.setDepartment(d8); a8.setDoctor(doc8); a8.setNurse(n8);
        apps.add(a8);

        // 9. Derma: Dobre Cristian, Camera 12, Dr. Stoica, Asist. Nistor
        Appointment a9 = new Appointment();
        a9.setAdmissionDate(LocalDate.now().plusDays(3)); a9.setStatus(AppointmentStatus.ACTIVE);
        a9.setPatient(p9); a9.setRoom(r9); a9.setDepartment(d9); a9.setDoctor(doc9); a9.setNurse(n9);
        apps.add(a9);

        // 10. Uro: Voicu Adrian, Camera Uro 2, Dr. Crisan, Asist. Florea
        Appointment a10 = new Appointment();
        a10.setAdmissionDate(LocalDate.now()); a10.setStatus(AppointmentStatus.ACTIVE);
        a10.setPatient(p10); a10.setRoom(r10); a10.setDepartment(d10); a10.setDoctor(doc10); a10.setNurse(n10);
        apps.add(a10);

        aRepo.saveAll(apps);

        // ---------------------------------------------------------------------
        // 8. MEDICAL STAFF APPOINTMENTS (10) - Legaturi explicite
        // ---------------------------------------------------------------------
        List<MedicalStaffAppointment> msas = new ArrayList<>();
        // Legam fiecare programare de doctorul principal
        for(int i=0; i<10; i++) {
            msas.add(new MedicalStaffAppointment(apps.get(i).getId(), doctors.get(i).getId(), "DOCTOR"));
        }

        mRepo.saveAll(msas);

        System.out.println("DataInitializer2: Baza de date a fost populata cu succes.");
    }
}