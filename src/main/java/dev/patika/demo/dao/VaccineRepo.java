package dev.patika.demo.dao;

import dev.patika.demo.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Long> {
    //Animal Id'sine göre yapılan aşıları bulmak için.
    List<Vaccine> findByAnimalsId(Long animalId);

    //Aynı tip aşının eklenmesini engellemek için.
    List<Vaccine> findByCodeAndAnimalsIdAndProtectionFinishDateAfter(String code, Long animalId, LocalDate protectionFinishDate);

    //Aşı koruyuculuk bitiş tarihi yaklaşanları listelemek için.
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate protectionStartDate, LocalDate protectionFinishDate);
}
