package com.fiap.customer.usecase;

import com.fiap.customer.controller.mapper.CustomerMapper;
import com.fiap.customer.domain.Address;
import com.fiap.customer.domain.Cpf;
import com.fiap.customer.domain.Customer;
import com.fiap.customer.exception.CustomerNotFoundException;
import com.fiap.customer.gateway.entity.AddressEntity;
import com.fiap.customer.gateway.entity.CustomerEntity;
import com.fiap.customer.gateway.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetCustomerByIdUseCaseTest {

    private CustomerRepository repository;
    private CustomerMapper mapper;
    private GetCustomerByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(CustomerRepository.class);
        mapper = mock(CustomerMapper.class);
        useCase = new GetCustomerByIdUseCase(repository, mapper);
    }

    @Test
    void shouldReturnCustomerWhenFound() {
        Long id = 1L;
        CustomerEntity entity = CustomerEntity.builder()
                .id(id)
                .name("João")
                .cpf("39053344705")
                .email("joao@email.com")
                .phone("11999999999")
                .birthDate(LocalDate.of(1990, 1, 1))
                .addresses(List.of(AddressEntity.builder()
                        .street("Rua A").number("123").cep("00000-000")
                        .city("Cidade").state("SP").complement(null).build()))
                .build();

        Customer expectedCustomer = new Customer(
                id,
                "João",
                new Cpf("39053344705"),
                "joao@email.com",
                "11999999999",
                LocalDate.of(1990, 1, 1),
                List.of(new Address("Rua A", "123", "00000-000", "Cidade", "SP", null)));

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(expectedCustomer);

        Customer actual = useCase.execute(id);

        assertEquals(expectedCustomer.getName(), actual.getName());
        assertEquals(expectedCustomer.getCpf().getValue(), actual.getCpf().getValue());
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        Long id = 2L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> useCase.execute(id));
    }
}
