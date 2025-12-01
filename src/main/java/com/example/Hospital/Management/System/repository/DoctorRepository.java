package com.example.Hospital.Management.System.repository;
import com.example.Hospital.Management.System.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository public interface DoctorRepository extends JpaRepository<Doctor, Long> {}
