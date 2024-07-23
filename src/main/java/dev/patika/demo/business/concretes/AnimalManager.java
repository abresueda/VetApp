package dev.patika.demo.business.concretes;

import dev.patika.demo.business.abstracts.IAnimalService;
import dev.patika.demo.core.exception.NotFoundException;
import dev.patika.demo.dao.AnimalRepo;
import dev.patika.demo.dto.response.Animal.AnimalResponse;
import dev.patika.demo.entities.Animal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalManager implements IAnimalService {
    private final AnimalRepo animalRepo;

    public AnimalManager(AnimalRepo animalRepo) {
        this.animalRepo = animalRepo;
    }

    @Override
    public Animal save(Animal animal) {
        return this.animalRepo.save(animal);
    }

    @Override
    public List<AnimalResponse> get(String name) {
        List<Animal> animals = this.animalRepo.findByName(name);
        if (animals.isEmpty()) {
            throw new NotFoundException("Hayvan bulunamadı.");
        }
        return List<AnimalResponse>;
    }

    @Override
    public Animal update(Animal animal) {
        this.get(animal.getId());
        return this.animalRepo.save(animal);
    }

    @Override
    public String delete(Long id) {
        Animal animal = this.get(id);
        this.animalRepo.delete(animal);
        return "Kayıtlı hayvan başarıyla silindi.";
    }
}
