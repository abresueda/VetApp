package dev.patika.demo.business.concretes;

import dev.patika.demo.business.abstracts.ICustomerService;
import dev.patika.demo.core.exception.NotFoundException;
import dev.patika.demo.core.exception.RecordAlreadyExistsException;
import dev.patika.demo.core.exception.RecordNotFoundException;
import dev.patika.demo.core.ulties.Message;
import dev.patika.demo.dao.CustomerRepo;
import dev.patika.demo.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerManager implements ICustomerService {
    private final CustomerRepo customerRepo;

    public CustomerManager(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer save(Customer customer) {
        //Optional yapısı ile null kontrolleriyle ilgili hatalar önlenir.
        Optional<Customer> existingCustomer = this.customerRepo.findByMail(customer.getMail());
        if (existingCustomer.isPresent()) {
            throw new RecordAlreadyExistsException("Kayıt sistemde mevcut!");
        }
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        if (!customerRepo.existsById(customer.getId())) {
            throw new RecordNotFoundException(customer.getId() + " ID'li kayıt sistemde bulunamadı.");
        }
        this.get(customer.getId());
        return this.customerRepo.save(customer);
    }

    //Customerları, isimlerine göre filtreleyerek getirmek için.
    @Override
    public List<Customer> getByName(String name) {
        List<Customer> customers = this.customerRepo.findByNameContainingIgnoreCase(name);
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
        if (!customerRepo.existsById(id)) {
            throw new RecordNotFoundException(id + " ID'li kayıt sistemde bulunamadı.");
        }
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return "Müşteri başarıyla silindi";
    }
}
