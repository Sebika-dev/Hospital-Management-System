package com.example.Hospital.Management.System.repository;
import com.example.Hospital.Management.System.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository public interface PatientRepository extends JpaRepository<Patient, Long> {}