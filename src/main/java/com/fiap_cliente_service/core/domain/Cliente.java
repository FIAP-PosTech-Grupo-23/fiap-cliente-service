package com.fiap_cliente_service.core.domain;

public class Cliente {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private String endereco;
    private String cpf;

    public Cliente(String nome, String email, String login, String senha, String endereco, String cpf) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.endereco = endereco;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }
}
