package com.fiap.cliente.usecase;

import com.fiap.cliente.domain.Cliente;
import com.fiap.cliente.domain.Cpf;
import com.fiap.cliente.exception.ClienteNotFoundException;
import com.fiap.cliente.gateway.entity.ClienteEntity;
import com.fiap.cliente.gateway.repository.ClienteRepository;
import com.fiap.cliente.mapper.ClienteMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarClientePorIdUseCaseTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private ClienteMapper mapper;

    @InjectMocks
    private BuscarClientePorIdUseCase useCase;

    private UUID uid;
    private ClienteEntity entity;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        uid = UUID.randomUUID();
        entity = ClienteEntity.builder()
                .uid(uid)
                .nome("João Silva")
                .cpf("12345678909")
                .email("joao@email.com")
                .telefone("11999999999")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .endereco("Rua das Flores, 123")
                .build();

        cliente = new Cliente(
                uid,
                "João Silva",
                new Cpf("12345678909"),
                "joao@email.com",
                "11999999999",
                LocalDate.of(1990, 1, 1),
                "Rua das Flores, 123"
        );
    }

    @Test
    void deveRetornarClienteQuandoEncontrado() {
        when(repository.findByUid(uid)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(cliente);

        Cliente resultado = useCase.execute(uid);

        assertNotNull(resultado);
        assertEquals(cliente, resultado);
        verify(repository).findByUid(uid);
        verify(mapper).toDomain(entity);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        when(repository.findByUid(uid)).thenReturn(Optional.empty());

        ClienteNotFoundException exception = assertThrows(
                ClienteNotFoundException.class,
                () -> useCase.execute(uid)
        );

        assertEquals("Cliente com ID " + uid + " não encontrado.", exception.getMessage());
        verify(repository).findByUid(uid);
        verify(mapper, never()).toDomain(any(ClienteEntity.class));
    }

    @Test
    void deveConsultarRepositorioComUidCorreto() {
        when(repository.findByUid(uid)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(cliente);

        useCase.execute(uid);

        verify(repository).findByUid(uid);
    }
}
