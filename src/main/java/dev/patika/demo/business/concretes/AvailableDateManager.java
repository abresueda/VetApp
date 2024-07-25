package dev.patika.demo.business.concretes;

import dev.patika.demo.business.abstracts.IAvailableService;
import dev.patika.demo.core.exception.NotFoundException;
import dev.patika.demo.core.exception.RecordAlreadyExistsException;
import dev.patika.demo.core.exception.RecordNotFoundException;
import dev.patika.demo.core.ulties.Message;
import dev.patika.demo.dao.AvailableDateRepo;
import dev.patika.demo.entities.AvailableDate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvailableDateManager implements IAvailableService {
    private final AvailableDateRepo availableDateRepo;

    public AvailableDateManager(AvailableDateRepo availableDateRepo) {
        this.availableDateRepo = availableDateRepo;
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        Optional<AvailableDate> existingDate = this.availableDateRepo.findByDoctorIdAndAvailableDate(
                availableDate.getDoctor().getId(), availableDate.getAvailableDate()
        );
        if (existingDate.isPresent()) {
            throw new RecordAlreadyExistsException("Bu tarihte zaten bir müsaitlik mecvut!");
        }
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        if (!availableDateRepo.existsById(availableDate.getId())) {
            throw new RecordNotFoundException(availableDate.getId() + " ID'li kayıt sistemde bulunamadı.");
        }
        this.get(availableDate.getId());
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate get(Long id) {
        return this.availableDateRepo.findById(id).orElseThrow(() -> new RecordNotFoundException(Message.NOT_FOUND));
    }

    @Override
    public String delete(Long id) {
        if (!availableDateRepo.existsById(id)) {
            throw new RecordNotFoundException(id + " ID'li kayıt sistemde bulunamadı.");
        }
        AvailableDate availableDate = this.get(id);
        this.availableDateRepo.delete(availableDate);
        return "Müsait gün başarıyla silindi.";
    }
}
