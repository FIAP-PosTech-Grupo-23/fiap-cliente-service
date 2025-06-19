package com.fiap_cliente_service.adapter;

import com.fiap_cliente_service.adapter.entity.ClienteEntity;
import com.fiap_cliente_service.adapter.repository.ClienteRepository;
import com.fiap_cliente_service.core.domain.Cliente;
import com.fiap_cliente_service.core.gateway.ClienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteJpaGateway implements ClienteGateway {

    private final ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Long criar(Cliente cliente) {

        ClienteEntity clienteEntity = new ClienteEntity(cliente.getNome(),
                cliente.getEmail(),
                cliente.getLogin(),
                cliente.getSenha(),
                cliente.getEndereco(),
                cliente.getCpf());

        return clienteRepository.save(clienteEntity).getId();

    }
}
