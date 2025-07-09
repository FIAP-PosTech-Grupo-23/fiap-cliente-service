package com.fiap.cliente.controller.response;

public record EnderecoResponse(
        String rua,
        String numero,
        String cep,
        String cidade,
        String estado,
        String complemento) {
}
