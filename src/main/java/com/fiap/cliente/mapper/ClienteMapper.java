package com.fiap.cliente.mapper;

import com.fiap.cliente.controller.request.ClienteRequest;
import com.fiap.cliente.controller.request.EnderecoRequest;
import com.fiap.cliente.controller.response.ClienteResponse;
import com.fiap.cliente.controller.response.EnderecoResponse;
import com.fiap.cliente.domain.Cliente;
import com.fiap.cliente.domain.Cpf;
import com.fiap.cliente.domain.Endereco;
import com.fiap.cliente.gateway.entity.ClienteEntity;
import com.fiap.cliente.gateway.entity.EnderecoEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClienteMapper {

        public Cliente toDomain(ClienteRequest request) {
                Endereco endereco = toDomain(request.endereco());

                return new Cliente(
                                request.nome(),
                                new Cpf(request.cpf()),
                                request.email(),
                                request.telefone(),
                                request.dataNascimento(),
                                endereco);
        }

        public Endereco toDomain(EnderecoRequest request) {
                return new Endereco(
                                request.rua(),
                                request.numero(),
                                request.cep(),
                                request.cidade(),
                                request.estado(),
                                request.complemento());
        }

        public ClienteResponse toResponse(Cliente cliente, UUID uid) {
                EnderecoResponse endereco = toResponse(cliente.getEndereco());

                return new ClienteResponse(
                                uid,
                                cliente.getNome(),
                                cliente.getCpf().getValue(),
                                cliente.getEmail(),
                                cliente.getTelefone(),
                                cliente.getDataNascimento(),
                                endereco);
        }

        public EnderecoResponse toResponse(Endereco endereco) {
                return new EnderecoResponse(
                                endereco.getRua(),
                                endereco.getNumero(),
                                endereco.getCep(),
                                endereco.getCidade(),
                                endereco.getEstado(),
                                endereco.getComplemento());
        }

        public ClienteEntity toEntity(Cliente cliente) {
                ClienteEntity clienteEntity = ClienteEntity.builder()
                                .uid(cliente.getUid())
                                .nome(cliente.getNome())
                                .cpf(cliente.getCpf().getValue())
                                .email(cliente.getEmail())
                                .telefone(cliente.getTelefone())
                                .dataNascimento(cliente.getDataNascimento())
                                .build();

                EnderecoEntity enderecoEntity = toEntity(cliente.getEndereco());
                enderecoEntity.setCliente(clienteEntity);

                clienteEntity.setEndereco(enderecoEntity);

                return clienteEntity;
        }

        public EnderecoEntity toEntity(Endereco endereco) {
                return EnderecoEntity.builder()
                                .rua(endereco.getRua())
                                .numero(endereco.getNumero())
                                .cep(endereco.getCep())
                                .cidade(endereco.getCidade())
                                .estado(endereco.getEstado())
                                .complemento(endereco.getComplemento())
                                .build();
        }

        public Cliente toDomain(ClienteEntity entity) {
                Endereco endereco = toDomain(entity.getEndereco());

                return new Cliente(
                                entity.getUid(),
                                entity.getNome(),
                                new Cpf(entity.getCpf()),
                                entity.getEmail(),
                                entity.getTelefone(),
                                entity.getDataNascimento(),
                                endereco);
        }

        public Endereco toDomain(EnderecoEntity entity) {
                return new Endereco(
                                entity.getRua(),
                                entity.getNumero(),
                                entity.getCep(),
                                entity.getCidade(),
                                entity.getEstado(),
                                entity.getComplemento());
        }
}
