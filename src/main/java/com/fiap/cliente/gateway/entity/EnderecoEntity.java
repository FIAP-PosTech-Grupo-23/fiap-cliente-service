package com.fiap.cliente.gateway.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "enderecos")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rua;
    private String numero;
    private String cep;
    private String cidade;
    private String estado;
    private String complemento;

    @OneToOne
    @JoinColumn(name = "cliente_uid", referencedColumnName = "uid")
    private ClienteEntity cliente;
}
