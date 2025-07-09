package com.fiap.cliente.usecase.validation;

import com.fiap.cliente.domain.Cliente;
import com.fiap.cliente.domain.Cpf;
import com.fiap.cliente.gateway.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidadorClienteTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ValidadorCliente validador;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(
                "João Silva",
                new Cpf("12345678909"),
                "joao@email.com",
                "11999999999",
                LocalDate.of(1990, 1, 1),
                "Rua das Flores, 123"
        );
    }

    @Test
    void deveValidarClienteComCpfUnico() {
        when(repository.existsByCpf("12345678909")).thenReturn(false);

        assertDoesNotThrow(() -> validador.validarParaCriacao(cliente));
        verify(repository).existsByCpf("12345678909");
    }

    @Test
    void deveLancarExcecaoQuandoCpfJaExiste() {
        when(repository.existsByCpf("12345678909")).thenReturn(true);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> validador.validarParaCriacao(cliente)
        );

        assertEquals("CPF já cadastrado: 12345678909", exception.getMessage());
        verify(repository).existsByCpf("12345678909");
    }

    @Test
    void deveConsultarRepositorioComCpfCorreto() {
        when(repository.existsByCpf("12345678909")).thenReturn(false);

        validador.validarParaCriacao(cliente);

        verify(repository).existsByCpf("12345678909");
    }
}
