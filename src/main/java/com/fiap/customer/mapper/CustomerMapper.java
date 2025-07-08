package com.fiap.customer.mapper;

import com.fiap.customer.controller.request.AddressRequest;
import com.fiap.customer.controller.request.CustomerRequest;
import com.fiap.customer.controller.response.AddressResponse;
import com.fiap.customer.controller.response.CustomerResponse;
import com.fiap.customer.domain.Address;
import com.fiap.customer.domain.Cpf;
import com.fiap.customer.domain.Customer;

import java.util.List;

public class CustomerMapper {

        public static Customer toDomain(CustomerRequest request) {
                List<Address> addresses = request.addresses().stream()
                                .map(CustomerMapper::toDomain)
                                .toList();

                return new Customer(
                                request.name(),
                                new Cpf(request.cpf()),
                                request.email(),
                                request.phone(),
                                request.birthDate(),
                                addresses);
        }

        public static Address toDomain(AddressRequest request) {
                return new Address(
                                request.street(),
                                request.number(),
                                request.cep(),
                                request.city(),
                                request.state(),
                                request.complement());
        }

        public static CustomerResponse toResponse(Customer customer) {
                List<AddressResponse> addresses = customer.getAddresses().stream()
                                .map(CustomerMapper::toResponse)
                                .toList();

                return new CustomerResponse(
                                customer.getId(),
                                customer.getName(),
                                customer.getCpf().getValue(),
                                customer.getEmail(),
                                customer.getPhone(),
                                customer.getBirthDate(),
                                addresses);
        }

        public static AddressResponse toResponse(Address address) {
                return new AddressResponse(
                                address.getStreet(),
                                address.getNumber(),
                                address.getCep(),
                                address.getCity(),
                                address.getState(),
                                address.getComplement());
        }
}
