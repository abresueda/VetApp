package dev.patika.demo.business.abstracts;

import dev.patika.demo.dto.response.Animal.AnimalResponse;
import dev.patika.demo.entities.Animal;

import java.util.List;

public interface IAnimalService {

    Animal save(Animal animal);

    List<Animal> get(String name);

    List<Animal> getByCustomerId(Long customerId);

    Animal update(Animal animal);

    String delete(Long id);

}
