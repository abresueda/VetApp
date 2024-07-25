package dev.patika.demo.business.concretes;

import dev.patika.demo.business.abstracts.IDoctorService;
import dev.patika.demo.core.exception.RecordAlreadyExistsException;
import dev.patika.demo.core.exception.RecordNotFoundException;
import dev.patika.demo.core.ulties.Message;
import dev.patika.demo.dao.DoctorRepo;
import dev.patika.demo.entities.Doctor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorManager implements IDoctorService {
    private final DoctorRepo doctorRepo;

    public DoctorManager(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public Doctor save (Doctor doctor) {
        Optional<Doctor> existingDoctor = this.doctorRepo.findByMail(doctor.getMail());
        if (existingDoctor.isPresent()) {
            throw new RecordAlreadyExistsException("Kayıt sistemde mecvut!");
        }
        return this.doctorRepo.save(doctor);
    }

    @Override
    public Doctor update (Doctor doctor) {
        if (!doctorRepo.existsById(doctor.getId())) {
            throw new RecordNotFoundException(doctor.getId() + " ID'li kayıt sistemde bulunamadı.");
        }
        this.get(doctor.getId());
        return this.doctorRepo.save(doctor);
    }

    @Override
    public Doctor get (Long id) {
        return this.doctorRepo.findById(id).orElseThrow(() -> new RecordNotFoundException(Message.NOT_FOUND));
    }

    @Override
    public String delete (Long id) {
        if (!doctorRepo.existsById(id)) {
            throw new RecordNotFoundException(id + " ID'li kayıt sistemde bulunamadı.");
        }
        Doctor doctor = this.get(id);
        this.doctorRepo.delete(doctor);
        return "Doktor başarıyla silindi.";
    }
}
