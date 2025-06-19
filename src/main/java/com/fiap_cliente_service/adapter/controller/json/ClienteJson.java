package com.fiap_cliente_service.adapter.controller.json;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteJson {

    private String nome;
    private String email;
    private String login;
    private String senha;
    private String endereco;
    private String cpf;

}
