package com.fiap.cliente.usecase;

import com.fiap.cliente.domain.Cliente;
import com.fiap.cliente.gateway.entity.ClienteEntity;
import com.fiap.cliente.gateway.repository.ClienteRepository;
import com.fiap.cliente.mapper.ClienteMapper;
import com.fiap.cliente.usecase.validation.ValidadorCliente;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CriarClienteUseCase {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;
    private final ValidadorCliente validadorCliente;

    public CriarClienteUseCase(ClienteRepository repository, ClienteMapper mapper, ValidadorCliente validadorCliente) {
        this.repository = repository;
        this.mapper = mapper;
        this.validadorCliente = validadorCliente;
    }

    public UUID execute(Cliente cliente) {
        validadorCliente.validarParaCriacao(cliente);
        
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
