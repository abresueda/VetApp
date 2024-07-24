package dev.patika.demo.business.concretes;

import dev.patika.demo.business.abstracts.IAppointmentService;
import dev.patika.demo.core.exception.NotFoundException;
import dev.patika.demo.core.ulties.Message;
import dev.patika.demo.dao.AppointmentRepo;
import dev.patika.demo.entities.Appointment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentManager implements IAppointmentService {
    private final AppointmentRepo appointmentRepo;

    public AppointmentManager(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public Appointment save(Appointment appointment) {
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) {
        this.get(appointment.getId());
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public List<Appointment> getByDoctorIdAndDateRange(Long doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        return this.appointmentRepo.findByDoctorIdAndDateBetween(doctorId, startDate, endDate);
    }

    @Override
    public List<Appointment> getByAnimalIdAndDateRange(Long animalId, LocalDateTime startDate, LocalDateTime endDate) {
        return this.appointmentRepo.findByAnimalIdAndDateBetween(animalId, startDate, endDate);
    }

    @Override
    public Appointment get(Long id) {
        return this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public String delete(Long id) {
        Appointment appointment = this.get(id);
        this.appointmentRepo.delete(appointment);
        return "Randevu başarıyla silindi.";
    }
}
