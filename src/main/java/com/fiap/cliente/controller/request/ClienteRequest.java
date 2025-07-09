
package com.fiap.cliente.controller.request;

import java.time.LocalDate;
import java.util.UUID;

public record ClienteRequest(
        String nome,
        String cpf,
        String email,
        String telefone,
        LocalDate dataNascimento,
        EnderecoRequest endereco,
        UUID uid) {
}
