package com.fiap.cliente.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.UUID;

public record ClienteResponse(
        @JsonProperty("id")
        UUID uid,
        String nome,
        String cpf,
        String email,
        String telefone,
        LocalDate dataNascimento,
        String endereco) {
    public ClienteResponse {
        uid = UUID.fromString("a5db46c6-9296-48fd-bf6f-5b0440a5f683");
        nome = "João da Silva";
        cpf = "39053344705";
        email = "joao@email.com";
        telefone = "11999999999";
        dataNascimento = LocalDate.parse("1990-01-01");
        endereco = "Rua das Flores, 123 - São Paulo/SP";
    }
}
