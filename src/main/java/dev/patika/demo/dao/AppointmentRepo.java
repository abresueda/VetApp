package dev.patika.demo.dao;

import dev.patika.demo.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdAndDateBetween(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);
    List<Appointment> findByAnimalIdAndDateBetween(Long animalId, LocalDateTime startDate, LocalDateTime endDate);
}
