package com.fiap_cliente_service.core.validador;

import com.fiap_cliente_service.core.domain.Cliente;
import com.fiap_cliente_service.core.exception.EmailInvalidoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidadorEmail implements Validador {
    @Override
    public void valida(Cliente cliente) {

        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

        if (cliente.getEmail() == null || !cliente.getEmail().matches(regex)) {
            throw new EmailInvalidoException("E-mail inválido: " + cliente.getEmail());
        }

    }
}
