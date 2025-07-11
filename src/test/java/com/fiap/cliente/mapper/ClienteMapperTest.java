package com.fiap.cliente.mapper;

import com.fiap.cliente.controller.request.ClienteRequest;
import com.fiap.cliente.controller.response.ClienteResponse;
import com.fiap.cliente.domain.Cliente;
import com.fiap.cliente.domain.Cpf;
import com.fiap.cliente.gateway.entity.ClienteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClienteMapperTest {

    private ClienteMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ClienteMapper();
    }

    @Test
    void deveConverterRequestParaDomain() {
        ClienteRequest request = new ClienteRequest(
                "João Silva",
                "12345678909",
                "joao@email.com",
                "11999999999",
                LocalDate.of(1990, 1, 1),
                "Rua das Flores, 123"
        );

        Cliente cliente = mapper.toDomain(request);

        assertEquals("João Silva", cliente.getNome());
        assertEquals("12345678909", cliente.getCpf().getValue());
        assertEquals("joao@email.com", cliente.getEmail());
        assertEquals("11999999999", cliente.getTelefone());
        assertEquals(LocalDate.of(1990, 1, 1), cliente.getDataNascimento());
        assertEquals("Rua das Flores, 123", cliente.getEndereco());
    }

    @Test
    void deveConverterDomainParaResponse() {
        UUID uid = UUID.randomUUID();
        Cliente cliente = new Cliente(
                uid,
                "João Silva",
                new Cpf("12345678909"),
                "joao@email.com",
                "11999999999",
                LocalDate.of(1990, 1, 1),
                "Rua das Flores, 123"
        );

        ClienteResponse response = mapper.toResponse(cliente, uid);

        assertEquals(uid, response.uid());
        assertEquals("João Silva", response.nome());
        assertEquals("12345678909", response.cpf());
        assertEquals("joao@email.com", response.email());
        assertEquals("11999999999", response.telefone());
        assertEquals(LocalDate.of(1990, 1, 1), response.dataNascimento());
        assertEquals("Rua das Flores, 123", response.endereco());
    }

    @Test
    void deveConverterDomainParaEntity() {
        UUID uid = UUID.randomUUID();
        Cliente cliente = new Cliente(
                uid,
                "João Silva",
                new Cpf("12345678909"),
                "joao@email.com",
                "11999999999",
                LocalDate.of(1990, 1, 1),
                "Rua das Flores, 123"
        );

        ClienteEntity entity = mapper.toEntity(cliente);

        assertEquals(uid, entity.getUid());
        assertEquals("João Silva", entity.getNome());
        assertEquals("12345678909", entity.getCpf());
        assertEquals("joao@email.com", entity.getEmail());
        assertEquals("11999999999", entity.getTelefone());
        assertEquals(LocalDate.of(1990, 1, 1), entity.getDataNascimento());
        assertEquals("Rua das Flores, 123", entity.getEndereco());
    }

    @Test
    void deveConverterEntityParaDomain() {
        UUID uid = UUID.randomUUID();
        ClienteEntity entity = ClienteEntity.builder()
                .uid(uid)
                .nome("João Silva")
                .cpf("12345678909")
                .email("joao@email.com")
                .telefone("11999999999")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .endereco("Rua das Flores, 123")
                .build();

        Cliente cliente = mapper.toDomain(entity);

        assertEquals(uid, cliente.getUid());
        assertEquals("João Silva", cliente.getNome());
        assertEquals("12345678909", cliente.getCpf().getValue());
        assertEquals("joao@email.com", cliente.getEmail());
        assertEquals("11999999999", cliente.getTelefone());
        assertEquals(LocalDate.of(1990, 1, 1), cliente.getDataNascimento());
        assertEquals("Rua das Flores, 123", cliente.getEndereco());
    }
}
