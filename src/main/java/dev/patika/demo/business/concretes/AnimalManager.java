package dev.patika.demo.business.concretes;

import dev.patika.demo.business.abstracts.IAnimalService;
import dev.patika.demo.core.exception.NotFoundException;
import dev.patika.demo.dao.AnimalRepo;
import dev.patika.demo.dao.CustomerRepo;
import dev.patika.demo.dto.response.Animal.AnimalResponse;
import dev.patika.demo.entities.Animal;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return this.animalRepo.save(animal);
    }

    //Animalları isme göre getirtmek için.
    @Override
    public List<Animal> get(String name) {
        List<Animal> animals = this.animalRepo.findByName(name);
        if (animals.isEmpty()) {
            throw new NotFoundException("Hayvan bulunamadı.");
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
        return this.animalRepo.save(animal);
    }

    @Override
    public String delete(Long id) {
        Animal animal = (Animal) this.get(String.valueOf(id));
        this.animalRepo.delete(animal);
        return "Kayıtlı hayvan başarıyla silindi.";
    }
}
