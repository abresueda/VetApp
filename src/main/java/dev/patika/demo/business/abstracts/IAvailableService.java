package dev.patika.demo.business.abstracts;

import dev.patika.demo.entities.AvailableDate;

public interface IAvailableService {

    AvailableDate save(AvailableDate availableDate);

    AvailableDate update(AvailableDate availableDate);

    AvailableDate get(Long id);

    String delete(Long id);
}
