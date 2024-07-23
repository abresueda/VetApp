package dev.patika.demo.business.abstracts;

import dev.patika.demo.entities.Doctor;

public interface IDoctorService {

    Doctor save(Doctor doctor);

    Doctor update(Doctor doctor);

    Doctor get(Long id);

    String delete(Long id);
}
