package com.fiap_cliente_service.adapter.controller;

import com.fiap_cliente_service.adapter.controller.json.ClienteJson;
import com.fiap_cliente_service.core.domain.Cliente;
import com.fiap_cliente_service.core.usecase.CriarClienteUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final CriarClienteUsecase criarClienteUsecase;

    @PostMapping
    public ResponseEntity<Long> criar(@RequestBody ClienteJson clienteJson) {

        Long id = criarClienteUsecase.criar(createDomain(clienteJson));

        return ResponseEntity.status(HttpStatus.CREATED).body(id);

    }

    private Cliente createDomain(ClienteJson clienteJson) {
        return new Cliente(clienteJson.getNome(),
                clienteJson.getEmail(),
                clienteJson.getLogin(),
                clienteJson.getSenha(),
                clienteJson.getEndereco(),
                clienteJson.getCpf());
    }


}
