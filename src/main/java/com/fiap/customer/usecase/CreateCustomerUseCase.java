package com.fiap.customer.usecase;

import com.fiap.customer.domain.Address;

import com.fiap.customer.domain.Customer;
import com.fiap.customer.gateway.entity.AddressEntity;
import com.fiap.customer.gateway.entity.CustomerEntity;
import com.fiap.customer.gateway.repository.CustomerRepository;
import com.fiap.customer.usecase.validation.CustomerValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateCustomerUseCase {

    private final CustomerRepository repository;
    private final CustomerValidator validator;

    public CreateCustomerUseCase(CustomerRepository repository, CustomerValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Long execute(Customer customer) {
        validator.validateForCreation(customer);
        CustomerEntity entity = toEntity(customer);
        return repository.save(entity).getId();
    }

    private CustomerEntity toEntity(Customer customer) {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .id(customer.getId())
                .name(customer.getName())
                .cpf(customer.getCpf().getValue())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .birthDate(customer.getBirthDate())
                .build();

        // lista temporária para armazenar os endereços
        List<AddressEntity> addressEntities = new java.util.ArrayList<>();

        for (Address address : customer.getAddresses()) {
            AddressEntity addressEntity = AddressEntity.builder()
                    .street(address.getStreet())
                    .number(address.getNumber())
                    .cep(address.getCep())
                    .city(address.getCity())
                    .state(address.getState())
                    .complement(address.getComplement())
                    .customer(customerEntity)
                    .build();

            addressEntities.add(addressEntity);
        }

        customerEntity.setAddresses(addressEntities);

        return customerEntity;
    }
}