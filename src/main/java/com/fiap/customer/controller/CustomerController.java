package com.fiap.customer.controller;

import com.fiap.customer.controller.request.CustomerRequest;
import com.fiap.customer.controller.response.CustomerResponse;
import com.fiap.customer.domain.Cpf;
import com.fiap.customer.domain.Customer;
import com.fiap.customer.usecase.CreateCustomer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CreateCustomer createCustomer;

    public CustomerController(CreateCustomer createCustomer) {
        this.createCustomer = createCustomer;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest request) {
        Customer customer = new Customer(
                request.name(),
                new Cpf(request.cpf()),
                request.email(),
                request.phone(),
                request.birthDate(),
                List.of() // futuramente: addresses
        );

        Long id = createCustomer.execute(customer);

        CustomerResponse response = new CustomerResponse(
                id,
                customer.getName(),
                customer.getCpf().getValue(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getBirthDate());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
