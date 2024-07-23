package dev.patika.demo.business.concretes;

import dev.patika.demo.business.abstracts.ICustomerService;
import dev.patika.demo.core.exception.NotFoundException;
import dev.patika.demo.core.ulties.Message;
import dev.patika.demo.dao.CustomerRepo;
import dev.patika.demo.entities.Animal;
import dev.patika.demo.entities.Customer;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.MediaSize;
import java.util.List;

@Service
public class CustomerManager implements ICustomerService {
    private final CustomerRepo customerRepo;

    public CustomerManager(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        this.get(customer.getId());
        return this.customerRepo.save(customer);
    }

    //Customerları, isimlerine göre filtreleyerek getirmek için.
    @Override
    public List<Customer> getByName(String name) {
        List<Customer> customers = this.customerRepo.findByName(name);
        if (customers.isEmpty()) {
            throw new NotFoundException("Müşteri bulunamadı.");
        }
        return customers;
    }

    @Override
    public Customer get(Long id) {
        return this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public String delete(Long id) {
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return "Müşteri başarıyla silindi";
    }
}
