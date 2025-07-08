package com.fiap.customer.controller.mapper;

import com.fiap.customer.controller.request.AddressRequest;
import com.fiap.customer.controller.request.CustomerRequest;
import com.fiap.customer.controller.response.AddressResponse;
import com.fiap.customer.controller.response.CustomerResponse;
import com.fiap.customer.domain.Address;
import com.fiap.customer.domain.Cpf;
import com.fiap.customer.domain.Customer;
import com.fiap.customer.gateway.entity.AddressEntity;
import com.fiap.customer.gateway.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {

        public Customer toDomain(CustomerRequest request) {
                List<Address> addresses = request.addresses().stream()
                                .map(this::toDomainAddress)
                                .toList();

                return new Customer(
                                request.name(),
                                new Cpf(request.cpf()),
                                request.email(),
                                request.phone(),
                                request.birthDate(),
                                addresses);
        }

        public CustomerResponse toResponse(Customer customer, Long id) {
                List<AddressResponse> addresses = customer.getAddresses().stream()
                                .map(this::toResponseAddress)
                                .toList();

                return new CustomerResponse(
                                id,
                                customer.getName(),
                                customer.getCpf().getValue(),
                                customer.getEmail(),
                                customer.getPhone(),
                                customer.getBirthDate(),
                                addresses);
        }

        private Address toDomainAddress(AddressRequest request) {
                return new Address(
                                request.street(),
                                request.number(),
                                request.cep(),
                                request.city(),
                                request.state(),
                                request.complement());
        }

        private AddressResponse toResponseAddress(Address address) {
                return new AddressResponse(
                                address.getStreet(),
                                address.getNumber(),
                                address.getCep(),
                                address.getCity(),
                                address.getState(),
                                address.getComplement());
        }

        public Customer toDomain(CustomerEntity entity) {
                List<Address> addresses = entity.getAddresses().stream()
                                .map(a -> new Address(
                                                a.getStreet(),
                                                a.getNumber(),
                                                a.getCep(),
                                                a.getCity(),
                                                a.getState(),
                                                a.getComplement()))
                                .toList();

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
