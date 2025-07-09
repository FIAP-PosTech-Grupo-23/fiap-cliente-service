package com.fiap.cliente.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "Dados de resposta de um cliente")
public record ClienteResponse(
        @JsonProperty("id")
        @Schema(description = "ID único do cliente", example = "a5db46c6-9296-48fd-bf6f-5b0440a5f683")
        UUID uid,
        
        @Schema(description = "Nome completo do cliente", example = "João da Silva")
        String nome,
        
        @Schema(description = "CPF do cliente", example = "12345678900")
        String cpf,
        
        @Schema(description = "Email do cliente", example = "joao@email.com")
        String email,
        
        @Schema(description = "Telefone do cliente", example = "11999999999")
        String telefone,
        
        @Schema(description = "Data de nascimento do cliente", example = "1990-01-01")
        LocalDate dataNascimento,
        
        @Schema(description = "Endereço completo do cliente", example = "Rua das Flores, 123 - São Paulo/SP")
        String endereco) {
}
