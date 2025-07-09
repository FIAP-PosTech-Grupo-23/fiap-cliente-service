package com.fiap.cliente.controller.response;

import java.time.LocalDate;
import java.util.UUID;

public record ClienteResponse(
    UUID uid,
    String nome,
    String cpf,
    String email,
    String telefone,
    LocalDate dataNascimento,
    EnderecoResponse endereco
) {}
