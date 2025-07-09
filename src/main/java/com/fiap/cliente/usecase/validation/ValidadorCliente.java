package com.fiap.cliente.usecase.validation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import com.fiap.cliente.domain.Cliente;
import com.fiap.cliente.gateway.repository.ClienteRepository;

@AllArgsConstructor
@Component
public class ValidadorCliente {

    private final ClienteRepository repository;

    public void validarParaCriacao(Cliente cliente) {
        validarCpfUnico(cliente);
    }

    private void validarCpfUnico(Cliente cliente) {
        if (repository.existsByCpf(cliente.getCpf().getValue())) {
            throw new RuntimeException("CPF já cadastrado: " + cliente.getCpf().getValue());
        }
    }
}
