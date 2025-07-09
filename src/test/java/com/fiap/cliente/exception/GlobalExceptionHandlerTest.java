package com.fiap.cliente.exception;

import com.fiap.cliente.controller.response.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void deveRetornarNotFoundParaClienteNotFoundException() {
        UUID uid = UUID.randomUUID();
        ClienteNotFoundException exception = new ClienteNotFoundException(uid);

        ResponseEntity<ErrorResponse> response = handler.handleNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(404, body.status());
        assertEquals("Cliente não encontrado.", body.message());
        assertEquals(1, body.errors().size());
        assertEquals("Cliente com ID " + uid + " não encontrado.", body.errors().get(0));
        assertNotNull(body.timestamp());
    }

    @Test
    void deveRetornarBadRequestParaValidationException() {
        String mensagem = "CPF já cadastrado: 12345678909";
        ValidationException exception = new ValidationException(mensagem);

        ResponseEntity<ErrorResponse> response = handler.handleValidation(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(400, body.status());
        assertEquals("Erro de validação.", body.message());
        assertEquals(1, body.errors().size());
        assertEquals(mensagem, body.errors().get(0));
        assertNotNull(body.timestamp());
    }

    @Test
    void deveRetornarBadRequestParaInvalidCpfException() {
        String cpf = "123456789";
        InvalidCpfException exception = new InvalidCpfException(cpf);

        ResponseEntity<ErrorResponse> response = handler.handleInvalidCpf(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(400, body.status());
        assertEquals("CPF inválido.", body.message());
        assertEquals(1, body.errors().size());
        assertEquals("Invalid CPF: " + cpf, body.errors().get(0));
        assertNotNull(body.timestamp());
    }

    @Test
    void deveRetornarInternalServerErrorParaExcecaoGenerica() {
        String mensagem = "Erro inesperado no sistema";
        Exception exception = new RuntimeException(mensagem);

        ResponseEntity<ErrorResponse> response = handler.handleGeneric(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        ErrorResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(500, body.status());
        assertEquals("Erro inesperado.", body.message());
        assertEquals(1, body.errors().size());
        assertEquals(mensagem, body.errors().get(0));
        assertNotNull(body.timestamp());
    }
}
