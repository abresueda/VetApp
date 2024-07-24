package dev.patika.demo.business.abstracts;

import dev.patika.demo.dto.response.Vaccine.VaccineWithAnimalsDTO;
import dev.patika.demo.entities.Vaccine;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {

    Vaccine save(Vaccine vaccine, Long animalId);

    Vaccine update(Vaccine vaccine);

    Vaccine get(Long id);

    List<Vaccine> getVaccinesByAnimalId(Long animalId);

    String delete(Long id);

    List<VaccineWithAnimalsDTO> getVaccinesByProtectionFinishDateBetween(LocalDate protectionStartDate, LocalDate protectionFinishDate);
}
