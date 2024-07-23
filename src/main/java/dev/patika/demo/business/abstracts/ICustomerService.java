package dev.patika.demo.business.abstracts;

import dev.patika.demo.entities.Customer;

import java.util.List;

public interface ICustomerService {

    Customer save(Customer customer);

    Customer update(Customer customer);

    List<Customer> getByName(String name);

    Customer get(Long id);

    String delete(Long id);
}
