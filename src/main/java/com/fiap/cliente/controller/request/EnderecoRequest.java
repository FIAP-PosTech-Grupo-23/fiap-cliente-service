package com.fiap.cliente.controller.request;

public record EnderecoRequest(
    String rua,
    String numero,
    String cep,
    String cidade,
    String estado,
    String complemento
) {}
