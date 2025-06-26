package com.fiap.customer.usecase.validation;

import com.fiap.customer.domain.Customer;
import com.fiap.customer.gateway.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {

    private final CustomerRepository repository;

    public CustomerValidator(CustomerRepository repository) {
        this.repository = repository;
    }

    public void validateForCreation(Customer customer) {
        validateCpfUniqueness(customer);

    }

    private void validateCpfUniqueness(Customer customer) {
        if (repository.existsByCpf(customer.getCpf().getValue())) {
            throw new RuntimeException("CPF já cadastrado: " + customer.getCpf().getValue());
        }
    }
}
