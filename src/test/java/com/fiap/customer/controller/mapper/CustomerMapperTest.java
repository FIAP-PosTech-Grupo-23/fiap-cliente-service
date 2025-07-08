package com.fiap.customer.controller.mapper;

import com.fiap.customer.controller.request.AddressRequest;
import com.fiap.customer.controller.request.CustomerRequest;
import com.fiap.customer.controller.response.CustomerResponse;
import com.fiap.customer.domain.Address;
import com.fiap.customer.domain.Cpf;
import com.fiap.customer.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private CustomerMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new CustomerMapper();
    }

    @Test
    void shouldMapRequestToDomain() {
        AddressRequest addressRequest = new AddressRequest("Rua A", "123", "00000-000", "Cidade", "SP", null);
        CustomerRequest request = new CustomerRequest("João", "39053344705", "joao@email.com", "11999999999",
                LocalDate.of(2000, 1, 1), List.of(addressRequest));

        Customer customer = mapper.toDomain(request);

        assertEquals("João", customer.getName());
        assertEquals("39053344705", customer.getCpf().getValue());
    }

    @Test
    void shouldMapDomainToResponse() {
        Address address = new Address("Rua A", "123", "00000-000", "Cidade", "SP", null);
        Customer customer = new Customer(1L, "João", new Cpf("39053344705"), "joao@email.com", "11999999999",
                LocalDate.of(2000, 1, 1), List.of(address));

        CustomerResponse response = mapper.toResponse(customer, 1L);

        assertEquals("João", response.name());
        assertEquals("39053344705", response.cpf());
    }
}
