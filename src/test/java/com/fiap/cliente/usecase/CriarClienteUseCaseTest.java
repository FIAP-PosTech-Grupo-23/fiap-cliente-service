package com.fiap.cliente.usecase;

import com.fiap.cliente.domain.Cliente;
import com.fiap.cliente.domain.Cpf;
import com.fiap.cliente.gateway.entity.ClienteEntity;
import com.fiap.cliente.gateway.repository.ClienteRepository;
import com.fiap.cliente.mapper.ClienteMapper;
import com.fiap.cliente.usecase.validation.ValidadorCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarClienteUseCaseTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private ClienteMapper mapper;

    @Mock
    private ValidadorCliente validadorCliente;

    @InjectMocks
    private CriarClienteUseCase useCase;

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
    void deveExecutarCriacaoComSucesso() {
        ClienteEntity entityMock = mock(ClienteEntity.class);
        when(mapper.toEntity(any(Cliente.class))).thenReturn(entityMock);
        when(repository.save(entityMock)).thenReturn(entityMock);

        UUID resultado = useCase.execute(cliente);

        assertNotNull(resultado);
        verify(validadorCliente).validarParaCriacao(cliente);
        verify(mapper).toEntity(any(Cliente.class));
        verify(repository).save(entityMock);
    }

    @Test
    void deveValidarClienteAntesDecriar() {
        ClienteEntity entityMock = mock(ClienteEntity.class);
        when(mapper.toEntity(any(Cliente.class))).thenReturn(entityMock);
        when(repository.save(entityMock)).thenReturn(entityMock);

        useCase.execute(cliente);

        verify(validadorCliente).validarParaCriacao(cliente);
    }

    @Test
    void deveGerarUidParaCliente() {
        ClienteEntity entityMock = mock(ClienteEntity.class);
        when(mapper.toEntity(any(Cliente.class))).thenReturn(entityMock);
        when(repository.save(entityMock)).thenReturn(entityMock);

        UUID resultado = useCase.execute(cliente);

        assertNotNull(resultado);
        assertTrue(resultado.toString().matches("^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$"));
    }

    @Test
    void deveSalvarClienteNoRepositorio() {
        ClienteEntity entityMock = mock(ClienteEntity.class);
        when(mapper.toEntity(any(Cliente.class))).thenReturn(entityMock);
        when(repository.save(entityMock)).thenReturn(entityMock);

        useCase.execute(cliente);

        verify(repository).save(entityMock);
    }
}
