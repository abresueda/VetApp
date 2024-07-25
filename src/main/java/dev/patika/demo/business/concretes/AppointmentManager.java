package dev.patika.demo.business.concretes;

import dev.patika.demo.business.abstracts.IAppointmentService;
import dev.patika.demo.core.exception.AppointmentConflictException;
import dev.patika.demo.core.exception.NotFoundException;
import dev.patika.demo.core.exception.RecordNotFoundException;
import dev.patika.demo.core.ulties.Message;
import dev.patika.demo.dao.AppointmentRepo;
import dev.patika.demo.dao.DoctorRepo;
import dev.patika.demo.entities.Appointment;
import dev.patika.demo.entities.Doctor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentManager implements IAppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final DoctorRepo doctorRepo;

    public AppointmentManager(AppointmentRepo appointmentRepo, DoctorRepo doctorRepo) {
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
    }

    @Override
    public Appointment save(Appointment appointment) {
        LocalDateTime appointmentDate = appointment.getDate();
        Doctor doctor = appointment.getDoctor();

        //Doktorun uygunluğunu kontrol etmek için.
        boolean isAvailable = isDoctorAvailable(doctor, appointmentDate);
        if (!isAvailable) {
            throw new AppointmentConflictException("Doktor bu tarihte çalışmamaktadır!/Girilen saatte başka bir randevusu mevcuttur.");
        }

        return this.appointmentRepo.save(appointment);
    }

    //Doktorun uygunluğunu kontrol etmek için, randevu saatleri arasında olup olmadığını kontrol edilir.
    @Override
    public boolean isDoctorAvailable(Doctor doctor, LocalDateTime date) {
        List<Appointment> appointments = this.appointmentRepo.findByDoctorIdAndDateBetween(
                //Saat başı değer elde etmek için "0" parametrelerini giriyoruz.
                doctor.getId(), date.withMinute(0).withSecond(0).withNano(0), date.plusHours(1)
        );
        //Randevuların boş olup olmadığı kontrol ediliyor. Liste boşsa(saat aralığında başka randevu yoksa) true döner.
        return appointments.isEmpty();
    }

    @Override
    public Appointment update(Appointment appointment) {
        if (!appointmentRepo.existsById(appointment.getId())) {
            throw new RecordNotFoundException(appointment.getId() + " ID'li kayıt sistemde bulunamadı.");
        }
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
        return this.appointmentRepo.findById(id).orElseThrow(() -> new RecordNotFoundException(Message.NOT_FOUND));
    }

    @Override
    public String delete(Long id) {
        if (!appointmentRepo.existsById(id)) {
            throw new RecordNotFoundException(id + " ID'li kayıt sistemde bulunamadı.");
        }
        Appointment appointment = this.get(id);
        this.appointmentRepo.delete(appointment);
        return "Randevu başarıyla silindi.";
    }
}
