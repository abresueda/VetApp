package dev.patika.demo.business.concretes;

import dev.patika.demo.business.abstracts.IAvailableService;
import dev.patika.demo.core.exception.NotFoundException;
import dev.patika.demo.core.ulties.Message;
import dev.patika.demo.dao.AvailableDateRepo;
import dev.patika.demo.entities.AvailableDate;
import org.springframework.stereotype.Service;

@Service
public class AvailableDateManager implements IAvailableService {
    private final AvailableDateRepo availableDateRepo;

    public AvailableDateManager(AvailableDateRepo availableDateRepo) {
        this.availableDateRepo = availableDateRepo;
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        this.get(availableDate.getId());
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate get(Long id) {
        return this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public String delete(Long id) {
        AvailableDate availableDate = this.get(id);
        this.availableDateRepo.delete(availableDate);
        return "Müsait gün başarıyla silindi.";
    }
}
