package com.fiap.customer.usecase.validation;

import com.fiap.customer.domain.Address;
import com.fiap.customer.domain.Cpf;
import com.fiap.customer.domain.Customer;
import com.fiap.customer.gateway.repository.CustomerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerValidatorTest {

    private CustomerRepository repository;
    private CustomerValidator validator;

    @BeforeEach
    void setup() {
        repository = mock(CustomerRepository.class);
        validator = new CustomerValidator(repository);
    }

    @Test
    void devePermitirCpfUnico() {
        Customer customer = clienteValido();
        when(repository.existsByCpf("12345678909")).thenReturn(false);

        assertDoesNotThrow(() -> validator.validateForCreation(customer));
    }

    @Test
    void deveLancarExcecaoQuandoCpfJaExiste() {
        Customer customer = clienteValido();
        when(repository.existsByCpf("12345678909")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> validator.validateForCreation(customer));
        assertTrue(exception.getMessage().contains("CPF já cadastrado"));
    }

    private Customer clienteValido() {
        Address address = new Address("Rua A", "123", "00000-000", "Cidade", "SP", null);
        return new Customer("João", new Cpf("12345678909"), "joao@email.com", "11999999999",
                LocalDate.of(2000, 1, 1), List.of(address));
    }
}
