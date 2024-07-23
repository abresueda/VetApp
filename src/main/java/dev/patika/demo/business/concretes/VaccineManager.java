package dev.patika.demo.business.concretes;

import dev.patika.demo.business.abstracts.IVaccineService;
import dev.patika.demo.core.exception.NotFoundException;
import dev.patika.demo.core.ulties.Message;
import dev.patika.demo.dao.VaccineRepo;
import dev.patika.demo.entities.Animal;
import dev.patika.demo.entities.Vaccine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineManager implements IVaccineService {
    private final VaccineRepo vaccineRepo;

    public VaccineManager(VaccineRepo vaccineRepo) {
        this.vaccineRepo = vaccineRepo;
    }

    @Override
    public Vaccine save(Vaccine vaccine) {
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        this.get(vaccine.getId());
        return this.vaccineRepo.save(vaccine);
    }

    //Vaccine id'sine göre vaccineları getirtmek için.
    @Override
    public Vaccine get(Long id) {
        return this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    //Animal id'sine göre, vaccineları getirtmek için.
    @Override
    public List<Vaccine> getVaccinesByAnimalId(Long animalId) {
        List<Vaccine> vaccines = this.vaccineRepo.findByAnimalsId(animalId);
        if (vaccines.isEmpty()) {
            throw new NotFoundException("Aşı bulunamadı");
        }
        return vaccines;
    }

    @Override
    public String delete(Long id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepo.delete(vaccine);
        return "Aşı başarıyla silindi.";
    }
}
