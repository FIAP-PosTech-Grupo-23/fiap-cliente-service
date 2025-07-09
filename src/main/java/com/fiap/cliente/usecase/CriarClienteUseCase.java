package com.fiap.cliente.usecase;

import com.fiap.cliente.domain.Cliente;
import com.fiap.cliente.gateway.entity.ClienteEntity;
import com.fiap.cliente.gateway.repository.ClienteRepository;
import com.fiap.cliente.mapper.ClienteMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CriarClienteUseCase {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public CriarClienteUseCase(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UUID execute(Cliente cliente) {
        UUID uid = UUID.randomUUID();

        Cliente clienteComUid = new Cliente(
                uid,
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getDataNascimento(),
                cliente.getEndereco());

        ClienteEntity entity = mapper.toEntity(clienteComUid);
        repository.save(entity);
        return uid;
    }
}
