package dev.patika.demo.business.concretes;

import dev.patika.demo.business.abstracts.IAnimalService;
import dev.patika.demo.core.exception.NotFoundException;
import dev.patika.demo.core.exception.RecordAlreadyExistsException;
import dev.patika.demo.core.exception.RecordNotFoundException;
import dev.patika.demo.dao.AnimalRepo;
import dev.patika.demo.dao.CustomerRepo;
import dev.patika.demo.dto.response.Animal.AnimalResponse;
import dev.patika.demo.entities.Animal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalManager implements IAnimalService {
    private final AnimalRepo animalRepo;
    private final CustomerRepo customerRepo;

    public AnimalManager(AnimalRepo animalRepo, CustomerRepo customerRepo) {
        this.animalRepo = animalRepo;
        this.customerRepo = customerRepo;
    }

    @Override
    public Animal save(Animal animal) {
        Optional<Animal> existingAnimal = this.animalRepo.findByNameAndSpecies(animal.getName(), animal.getSpecies());
        if (existingAnimal.isPresent()) {
            throw new RecordAlreadyExistsException("Hayvan sistemde mecvut!");
        }
        return this.animalRepo.save(animal);
    }

    //Animalları isme göre getirtmek için.
    @Override
    public List<Animal> get (String name) {
        List<Animal> animals = this.animalRepo.findByName(name);
        if (animals.isEmpty()) {
            throw new RecordNotFoundException("Hayvan bulunamadı.");
        }
        return animals;
    }

    //Animalları, Customerlara göre getirtmek için.
    @Override
    public List<Animal> getByCustomerId(Long customerId) {
        List<Animal> animals = this.animalRepo.findByCustomerId(customerId);
        if (animals.isEmpty()) {
            throw new NotFoundException("Hayvan bulunamadı.");
        }
        return animals;
    }

    @Override
    public Animal update(Animal animal) {
        if (!animalRepo.existsById(animal.getId())) {
            throw new RecordNotFoundException(animal.getId() + " ID'li kayıt sistemde bulunamadı.");
        }
        return this.animalRepo.save(animal);
    }

    @Override
    public String delete(Long id) {
        Animal animal = this.animalRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id + " ID kayıtlı hayvan sistemde bulunamadı."));
        this.animalRepo.delete(animal);
        return "Kayıtlı hayvan başarıyla silindi.";
    }
}
