package com.fiap.cliente.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "cpf")
public class Cliente {

    private UUID uid;
    private final String nome;
    private final Cpf cpf;
    private final String email;
    private String telefone;
    private final LocalDate dataNascimento;
    private final Endereco endereco;

    public Cliente(String nome, Cpf cpf, String email, String telefone, LocalDate dataNascimento,
            Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public Cliente(UUID uid, String nome, Cpf cpf, String email, String telefone, LocalDate dataNascimento,
            Endereco endereco) {
        this(nome, cpf, email, telefone, dataNascimento, endereco);
        this.uid = uid;
    }
}
