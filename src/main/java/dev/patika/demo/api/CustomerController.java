package dev.patika.demo.api;

import dev.patika.demo.business.abstracts.ICustomerService;
import dev.patika.demo.core.config.modelMapper.IModelMapperService;
import dev.patika.demo.core.result.Result;
import dev.patika.demo.core.result.ResultData;
import dev.patika.demo.core.ulties.ResultHelper;
import dev.patika.demo.dto.request.Customer.CustomerRequest;
import dev.patika.demo.dto.response.Customer.CustomerResponse;
import dev.patika.demo.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/customers")
public class CustomerController {
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    public CustomerController(ICustomerService customerService, IModelMapperService modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerRequest customerRequest) {
        Customer saveCustomer = this.modelMapper.forRequest().map(customerRequest, Customer.class);
        this.customerService.save(saveCustomer);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCustomer, CustomerResponse.class));
    }

    //Customerları isimlerine göre getirtmek için.
    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<Customer>> getByName(@RequestParam("name") String name) {
        List<Customer> customers = this.customerService.getByName(name);
        return new ResultData<>(true, "Müşteri bulundu.", "200", customers);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerRequest customerRequest) {
        Customer updateCustomer = this.modelMapper.forRequest().map(customerRequest, Customer.class);
        this.customerService.update(updateCustomer);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateCustomer, CustomerResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.customerService.delete(id);
        return ResultHelper.ok();
    }
}
