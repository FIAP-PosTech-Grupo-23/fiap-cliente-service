package com.fiap_cliente_service.core.usecase;

import com.fiap_cliente_service.core.domain.Cliente;
import com.fiap_cliente_service.core.gateway.ClienteGateway;
import com.fiap_cliente_service.core.validador.ClienteValidador;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarClienteUseCaseImpl implements CriarClienteUsecase {

    private final ClienteGateway clienteGateway;
    private final ClienteValidador clienteValidador;
    @Override
    public Long criar(Cliente cliente) {

        clienteValidador.valida(cliente);

        Long id = clienteGateway.criar(cliente);

        return id;
    }
}
