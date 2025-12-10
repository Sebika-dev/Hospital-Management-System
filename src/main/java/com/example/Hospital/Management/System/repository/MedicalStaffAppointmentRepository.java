package com.example.Hospital.Management.System.repository;
import com.example.Hospital.Management.System.model.MedicalStaffAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository public interface MedicalStaffAppointmentRepository extends JpaRepository<MedicalStaffAppointment, Long>, JpaSpecificationExecutor<MedicalStaffAppointment> {

}