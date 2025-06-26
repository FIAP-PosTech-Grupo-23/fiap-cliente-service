package com.fiap.customer.usecase;

import com.fiap.customer.domain.Customer;
import com.fiap.customer.gateway.entity.CustomerEntity;
import com.fiap.customer.gateway.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomer {

    private final CustomerRepository repository;
    private final CustomerValidator validator;

    public CreateCustomer(CustomerRepository repository, CustomerValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Long execute(Customer customer) {
        validator.validateForCreation(customer);

        CustomerEntity entity = CustomerEntity.builder()
                .name(customer.getName())
                .cpf(customer.getCpf().getValue())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .birthDate(customer.getBirthDate())
                .build();

        return repository.save(entity).getId();
    }
}
