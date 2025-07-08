package com.fiap.customer.controller;

import com.fiap.customer.controller.mapper.CustomerMapper;
import com.fiap.customer.controller.request.CustomerRequest;
import com.fiap.customer.controller.response.CustomerResponse;
import com.fiap.customer.domain.Customer;
import com.fiap.customer.usecase.CreateCustomerUseCase;
import com.fiap.customer.usecase.GetCustomerByIdUseCase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    private CreateCustomerUseCase createUseCase;
    private GetCustomerByIdUseCase getUseCase;
    private CustomerMapper mapper;
    private CustomerController controller;

    @BeforeEach
    void setup() {
        createUseCase = mock(CreateCustomerUseCase.class);
        getUseCase = mock(GetCustomerByIdUseCase.class);
        mapper = mock(CustomerMapper.class);
        controller = new CustomerController(createUseCase, getUseCase, mapper);
    }

    @Test
    void shouldCreateCustomer() {
        CustomerRequest request = mock(CustomerRequest.class);
        Customer customer = mock(Customer.class);
        CustomerResponse response = new CustomerResponse(1L, "João", "39053344705", "joao@email.com", "11999999999",
                LocalDate.of(1990, 5, 10), List.of());

        when(mapper.toDomain(request)).thenReturn(customer);
        when(createUseCase.execute(customer)).thenReturn(1L);
        when(mapper.toResponse(customer, 1L)).thenReturn(response);

        ResponseEntity<CustomerResponse> result = controller.create(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        assertEquals(response, result.getBody());
    }

    @Test
    void shouldReturnCustomerById() {
        Long id = 1L;
        Customer customer = mock(Customer.class);
        CustomerResponse response = new CustomerResponse(id, "João", "39053344705", "joao@email.com", "11999999999",
                LocalDate.of(1990, 5, 10), List.of());

        when(getUseCase.execute(id)).thenReturn(customer);
        when(customer.getId()).thenReturn(id);
        when(mapper.toResponse(customer, id)).thenReturn(response);

        ResponseEntity<CustomerResponse> result = controller.findById(id);
        assertEquals(HttpStatus.OK, result.getStatusCode());

        assertEquals(response, result.getBody());
    }
}
