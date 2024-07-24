package dev.patika.demo.business.concretes;

import dev.patika.demo.business.abstracts.IVaccineService;
import dev.patika.demo.core.config.modelMapper.IModelMapperService;
import dev.patika.demo.core.exception.NotFoundException;
import dev.patika.demo.core.ulties.Message;
import dev.patika.demo.dao.AnimalRepo;
import dev.patika.demo.dao.VaccineRepo;
import dev.patika.demo.dto.response.Animal.AnimalResponse;
import dev.patika.demo.dto.response.Vaccine.VaccineWithAnimalsDTO;
import dev.patika.demo.entities.Animal;
import dev.patika.demo.entities.Vaccine;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineManager implements IVaccineService {
    private final VaccineRepo vaccineRepo;
    private final AnimalRepo animalRepo;
    private final ModelMapper modelMapper;

    public VaccineManager(VaccineRepo vaccineRepo, AnimalRepo animalRepo, ModelMapper modelMapper) {
        this.vaccineRepo = vaccineRepo;
        this.animalRepo = animalRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Vaccine save(Vaccine vaccine, Long animalId) {
        List<Vaccine> vaccineProtection = this.vaccineRepo.findByCodeAndAnimalsIdAndProtectionFinishDateAfter(
                vaccine.getCode(), animalId, LocalDate.now());
        if (!vaccineProtection.isEmpty()) {
            throw new IllegalStateException("Aşı koruyuculuğu hala geçerlidir.");
        }

        Animal animal = animalRepo.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException(animalId + "Id'ye sahip hayvan bulunamadı."));

        //Vaccine'e hayvanı eklemek için.
        vaccine.getAnimals().add(animal);
        animal.getVaccines().add(vaccine);

        this.vaccineRepo.save(vaccine);
        this.animalRepo.save(animal);

        return vaccine;
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

    //Aşı koruyuculuk bitiş tarihi yaklaşanlar.
    @Override
    public List<VaccineWithAnimalsDTO> getVaccinesByProtectionFinishDateBetween(LocalDate protectionStartDate, LocalDate protectionFinishDate) {
        List<Vaccine> vaccines = this.vaccineRepo.findByProtectionFinishDateBetween(protectionStartDate, protectionFinishDate);
        return vaccines.stream().map(vaccine -> {
            List<AnimalResponse> animalResponses = vaccine.getAnimals().stream()
                    .map(animal -> modelMapper.map(animal, AnimalResponse.class))
                    .collect(Collectors.toList());

            VaccineWithAnimalsDTO vaccineWithAnimalsDTO = modelMapper.map(vaccine, VaccineWithAnimalsDTO.class);
            vaccineWithAnimalsDTO.setAnimals(animalResponses);
            return vaccineWithAnimalsDTO;
        }).collect(Collectors.toList());
        //return this.vaccineRepo.findByProtectionFinishDateBetween(protectionStartDate, protectionFinishDate);
    }

    @Override
    public String delete(Long id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepo.delete(vaccine);
        return "Aşı başarıyla silindi.";
    }

}
