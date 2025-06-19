package com.fiap_cliente_service.core.validador;

import com.fiap_cliente_service.core.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidadorCPF implements Validador {
    @Override
    public void valida(Cliente cliente) {
        String cpf = cliente.getCpf();

        if (cpf == null) {
            throw new IllegalArgumentException("CPF não pode ser nulo");
        }

        // Remove pontos e traço
        String cpfNumerico = cpf.replaceAll("[^\\d]", "");

        if (cpfNumerico.length() != 11 || cpfNumerico.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("CPF inválido");
        }

        // Validação dos dígitos verificadores
        if (!isValidCPF(cpfNumerico)) {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    private static boolean isValidCPF(String cpf) {
        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int digito1 = 11 - (soma % 11);
            if (digito1 >= 10) digito1 = 0;

            if (digito1 != Character.getNumericValue(cpf.charAt(9))) return false;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int digito2 = 11 - (soma % 11);
            if (digito2 >= 10) digito2 = 0;

            return digito2 == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }
}
