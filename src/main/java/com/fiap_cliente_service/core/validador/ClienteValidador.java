package com.fiap_cliente_service.core.validador;

import com.fiap_cliente_service.core.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteValidador implements Validador{

    private final List<Validador> validators;

    public ClienteValidador() {
        this.validators = List.of(
                new ValidadorEmail(),
                new ValidadorCPF()
        );
    }


    @Override
    public void valida(Cliente cliente) {
        for (Validador validator : validators) {
            validator.valida(cliente);
        }

    }
}
