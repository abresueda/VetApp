package dev.patika.demo.dao;

import dev.patika.demo.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Long> {
    List<Animal> findByName(String name);
    List<Animal> findByCustomerId(Long customerId);
    Optional<Animal> findByNameAndSpecies(String name, String species);
}
