
package com.fiap.cliente.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Dados para criação de um cliente")
public record ClienteRequest(
        @Schema(description = "Nome completo do cliente", example = "João da Silva")
        String nome,
        
        @Schema(description = "CPF do cliente (com ou sem formatação)", example = "12345678900")
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
