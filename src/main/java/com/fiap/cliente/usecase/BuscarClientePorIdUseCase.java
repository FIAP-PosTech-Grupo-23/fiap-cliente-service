package com.fiap.cliente.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fiap.cliente.domain.Cliente;
import com.fiap.cliente.exception.ClienteNotFoundException;
import com.fiap.cliente.gateway.entity.ClienteEntity;
import com.fiap.cliente.gateway.repository.ClienteRepository;
import com.fiap.cliente.mapper.ClienteMapper;

@Service
public class BuscarClientePorIdUseCase {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public BuscarClientePorIdUseCase(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Cliente execute(UUID uid) {
        ClienteEntity entity = repository.findByUid(uid)
                .orElseThrow(() -> new ClienteNotFoundException(uid));
        return mapper.toDomain(entity);
    }
}
