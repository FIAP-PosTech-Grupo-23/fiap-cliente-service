package com.fiap.cliente.mapper;

import com.fiap.cliente.controller.request.ClienteRequest;
import com.fiap.cliente.controller.response.ClienteResponse;
import com.fiap.cliente.domain.Cliente;
import com.fiap.cliente.domain.Cpf;
import com.fiap.cliente.gateway.entity.ClienteEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClienteMapper {

        public Cliente toDomain(ClienteRequest request) {
                return new Cliente(
                                request.nome(),
                                new Cpf(request.cpf()),
                                request.email(),
                                request.telefone(),
                                request.dataNascimento(),
                                request.endereco());
        }

        public ClienteResponse toResponse(Cliente cliente, UUID uid) {
                return new ClienteResponse(
                                uid,
                                cliente.getNome(),
                                cliente.getCpf().getValue(),
                                cliente.getEmail(),
                                cliente.getTelefone(),
                                cliente.getDataNascimento(),
                                cliente.getEndereco());
        }

        public ClienteEntity toEntity(Cliente cliente) {
                return ClienteEntity.builder()
                                .uid(cliente.getUid())
                                .nome(cliente.getNome())
                                .cpf(cliente.getCpf().getValue())
                                .email(cliente.getEmail())
                                .telefone(cliente.getTelefone())
                                .dataNascimento(cliente.getDataNascimento())
                                .endereco(cliente.getEndereco())
                                .build();
        }

        public Cliente toDomain(ClienteEntity entity) {
                return new Cliente(
                                entity.getUid(),
                                entity.getNome(),
                                new Cpf(entity.getCpf()),
                                entity.getEmail(),
                                entity.getTelefone(),
                                entity.getDataNascimento(),
                                entity.getEndereco());
        }
}
