package dev.patika.demo.business.abstracts;

import dev.patika.demo.entities.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {

    Appointment save(Appointment appointment);

    Appointment update(Appointment appointment);

    List<Appointment> getByDoctorIdAndDateRange(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);

    List<Appointment> getByAnimalIdAndDateRange(Long animalId, LocalDateTime startDate, LocalDateTime endDate);

    Appointment get(Long id);

    String delete(Long id);
}
