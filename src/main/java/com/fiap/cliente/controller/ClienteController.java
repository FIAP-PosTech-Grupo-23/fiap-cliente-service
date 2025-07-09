package com.fiap.cliente.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.cliente.controller.request.ClienteRequest;
import com.fiap.cliente.controller.response.ClienteResponse;
import com.fiap.cliente.domain.Cliente;
import com.fiap.cliente.mapper.ClienteMapper;
import com.fiap.cliente.usecase.CriarClienteUseCase;
import com.fiap.cliente.usecase.BuscarClientePorIdUseCase;

import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final CriarClienteUseCase criarClienteUseCase;
    private final BuscarClientePorIdUseCase buscarClientePorIdUseCase;
    private final ClienteMapper clienteMapper;

    public ClienteController(CriarClienteUseCase criarClienteUseCase, BuscarClientePorIdUseCase buscarClientePorIdUseCase, ClienteMapper clienteMapper) {
        this.criarClienteUseCase = criarClienteUseCase;
        this.buscarClientePorIdUseCase = buscarClientePorIdUseCase;
        this.clienteMapper = clienteMapper;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> criar(@RequestBody ClienteRequest request) {
        Cliente cliente = clienteMapper.toDomain(request);
        UUID id = criarClienteUseCase.execute(cliente);
        ClienteResponse response = clienteMapper.toResponse(cliente, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable UUID uid) {
        Cliente cliente = buscarClientePorIdUseCase.execute(uid);
        ClienteResponse response = clienteMapper.toResponse(cliente, uid);
        return ResponseEntity.ok(response);
    }
}
