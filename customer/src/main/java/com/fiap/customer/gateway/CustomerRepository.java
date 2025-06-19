package com.fiap.customer.gateway;

import com.fiap.customer.domain.Customer;

import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);

        Optional<Customer> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}