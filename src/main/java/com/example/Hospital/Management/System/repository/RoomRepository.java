package com.example.Hospital.Management.System.repository;
import com.example.Hospital.Management.System.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository public interface RoomRepository extends JpaRepository<Room, Long> {}
