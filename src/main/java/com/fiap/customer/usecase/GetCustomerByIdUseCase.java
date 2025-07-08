package com.fiap.customer.usecase;

import com.fiap.customer.domain.Address;
import com.fiap.customer.domain.Cpf;
import com.fiap.customer.domain.Customer;
import com.fiap.customer.gateway.entity.AddressEntity;
import com.fiap.customer.gateway.entity.CustomerEntity;
import com.fiap.customer.gateway.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCustomerByIdUseCase {

    private final CustomerRepository repository;

    public GetCustomerByIdUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer execute(Long id) {
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        List<Address> addresses = entity.getAddresses().stream().map(address -> new Address(
                address.getStreet(),
                address.getNumber(),
                address.getCep(),
                address.getCity(),
                address.getState(),
                address.getComplement())).toList();

        return new Customer(
                entity.getId(),
                entity.getName(),
                new Cpf(entity.getCpf()),
                entity.getEmail(),
                entity.getPhone(),
                entity.getBirthDate(),
                addresses);
    }
}
