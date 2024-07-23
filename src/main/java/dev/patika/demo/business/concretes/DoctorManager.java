package dev.patika.demo.business.concretes;

import dev.patika.demo.business.abstracts.IDoctorService;
import dev.patika.demo.core.exception.NotFoundException;
import dev.patika.demo.core.ulties.Message;
import dev.patika.demo.dao.DoctorRepo;
import dev.patika.demo.entities.Doctor;
import org.springframework.stereotype.Service;

@Service
public class DoctorManager implements IDoctorService {
    private final DoctorRepo doctorRepo;

    public DoctorManager(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public Doctor save (Doctor doctor) {
        return this.doctorRepo.save(doctor);
    }

    @Override
    public Doctor update (Doctor doctor) {
        this.get(doctor.getId());
        return this.doctorRepo.save(doctor);
    }

    @Override
    public Doctor get (Long id) {
        return this.doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public String delete (Long id) {
        Doctor doctor = this.get(id);
        this.doctorRepo.delete(doctor);
        return "Doktor başarıyla silindi.";
    }
}
