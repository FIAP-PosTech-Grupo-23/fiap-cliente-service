package com.fiap.customer.controller;

import com.fiap.customer.controller.mapper.CustomerMapper;
import com.fiap.customer.controller.request.CustomerRequest;
import com.fiap.customer.controller.response.CustomerResponse;
import com.fiap.customer.domain.Customer;
import com.fiap.customer.usecase.CreateCustomerUseCase;
import com.fiap.customer.usecase.GetCustomerByIdUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final CustomerMapper customerMapper;

    public CustomerController(
            CreateCustomerUseCase createCustomerUseCase,
            GetCustomerByIdUseCase getCustomerByIdUseCase,
            CustomerMapper customerMapper) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.getCustomerByIdUseCase = getCustomerByIdUseCase;
        this.customerMapper = customerMapper;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest request) {
        Customer customer = customerMapper.toDomain(request);
        Long id = createCustomerUseCase.execute(customer);
        CustomerResponse response = customerMapper.toResponse(customer, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable Long id) {
        Customer customer = getCustomerByIdUseCase.execute(id);
        CustomerResponse response = customerMapper.toResponse(customer, customer.getId());
        return ResponseEntity.ok(response);
    }
}
