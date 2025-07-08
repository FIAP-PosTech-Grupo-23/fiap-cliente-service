package com.fiap.customer.usecase;

import com.fiap.customer.domain.Address;
import com.fiap.customer.domain.Cpf;
import com.fiap.customer.domain.Customer;
import com.fiap.customer.gateway.entity.CustomerEntity;
import com.fiap.customer.gateway.repository.CustomerRepository;
import com.fiap.customer.usecase.validation.CustomerValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCustomerUseCaseTest {

    private CustomerRepository repository;
    private CustomerValidator validator;
    private CreateCustomerUseCase useCase;

    @BeforeEach
    void setup() {
        repository = mock(CustomerRepository.class);
        validator = mock(CustomerValidator.class);
        useCase = new CreateCustomerUseCase(repository, validator);
    }

    @Test
    void shouldCreateCustomerSuccessfully() {
        Customer customer = validCustomer();
        CustomerEntity savedEntity = CustomerEntity.builder().id(1L).build();

        when(repository.save(any())).thenReturn(savedEntity);

        Long id = useCase.execute(customer);

        assertEquals(1L, id);
        verify(validator).validateForCreation(customer);
        verify(repository).save(any());
    }

    @Test
    void shouldThrowWhenValidationFails() {
        Customer customer = validCustomer();
        doThrow(new RuntimeException("CPF duplicado")).when(validator).validateForCreation(customer);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> useCase.execute(customer));
        assertEquals("CPF duplicado", ex.getMessage());
    }

    @Test
    void shouldMapCustomerToEntityProperly() {
        Customer customer = validCustomer();
        when(repository.save(any())).thenAnswer(invocation -> {
            CustomerEntity entity = invocation.getArgument(0);
            assertEquals("João", entity.getName());
            assertEquals("39053344705", entity.getCpf());
            return CustomerEntity.builder().id(2L).build();
        });

        Long id = useCase.execute(customer);
        assertEquals(2L, id);
    }

    private Customer validCustomer() {
        Address address = new Address("Rua X", "123", "00000-000", "São Paulo", "SP", null);
        return new Customer("João", new Cpf("39053344705"), "joao@email.com", "11999999999",
                LocalDate.of(2000, 1, 1), List.of(address));
    }
}
