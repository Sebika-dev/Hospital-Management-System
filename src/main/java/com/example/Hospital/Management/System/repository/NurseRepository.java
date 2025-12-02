package com.example.Hospital.Management.System.repository;
import com.example.Hospital.Management.System.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository public interface NurseRepository extends JpaRepository<Nurse, Long> {}