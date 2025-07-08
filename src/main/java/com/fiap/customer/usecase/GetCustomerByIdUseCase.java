package com.fiap.customer.usecase;

import com.fiap.customer.controller.mapper.CustomerMapper;
import com.fiap.customer.domain.Customer;
import com.fiap.customer.exception.CustomerNotFoundException;
import com.fiap.customer.gateway.entity.CustomerEntity;
import com.fiap.customer.gateway.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class GetCustomerByIdUseCase {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public GetCustomerByIdUseCase(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Customer execute(Long id) {
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        return mapper.toDomain(entity);
    }
}
