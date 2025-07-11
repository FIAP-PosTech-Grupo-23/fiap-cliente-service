package com.fiap.cliente.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.fiap.cliente.controller.request.ClienteRequest;
import com.fiap.cliente.controller.response.ClienteResponse;
import com.fiap.cliente.domain.Cliente;
import com.fiap.cliente.mapper.ClienteMapper;
import com.fiap.cliente.usecase.CriarClienteUseCase;
import com.fiap.cliente.usecase.BuscarClientePorIdUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
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
    @Operation(summary = "Criar novo cliente", description = "Cria um novo cliente no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso",
                content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou CPF já cadastrado",
                content = @Content(schema = @Schema(implementation = com.fiap.cliente.controller.response.ErrorResponse.class)))
    })
    public ResponseEntity<ClienteResponse> criar(@RequestBody ClienteRequest request) {
        Cliente cliente = clienteMapper.toDomain(request);
        UUID id = criarClienteUseCase.execute(cliente);
        ClienteResponse response = clienteMapper.toResponse(cliente, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{uid}")
    @Operation(summary = "Buscar cliente por ID", description = "Busca um cliente específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                content = @Content(schema = @Schema(implementation = com.fiap.cliente.controller.response.ErrorResponse.class)))
    })
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable UUID uid) {
        Cliente cliente = buscarClientePorIdUseCase.execute(uid);
        ClienteResponse response = clienteMapper.toResponse(cliente, uid);
        return ResponseEntity.ok(response);
    }
}
