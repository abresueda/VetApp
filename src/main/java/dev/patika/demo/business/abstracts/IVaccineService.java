package dev.patika.demo.business.abstracts;

import dev.patika.demo.entities.Vaccine;

import java.util.List;

public interface IVaccineService {

    Vaccine save(Vaccine vaccine);

    Vaccine update(Vaccine vaccine);

    Vaccine get(Long id);

    List<Vaccine> getVaccinesByAnimalId(Long animalId);

    String delete(Long id);
}
