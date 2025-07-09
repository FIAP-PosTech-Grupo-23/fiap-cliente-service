package com.fiap.cliente.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Resposta de erro da API")
public record ErrorResponse(
        @Schema(description = "Código de status HTTP", example = "400")
        int status,
        
        @Schema(description = "Mensagem de erro", example = "Erro de validação")
        String message,
        
        @Schema(description = "Lista de erros específicos", example = "[\"CPF já cadastrado: 12345678900\"]")
        List<String> errors,
        
        @Schema(description = "Timestamp do erro", example = "2025-07-09T03:38:46.347385108")
        LocalDateTime timestamp) {
}
