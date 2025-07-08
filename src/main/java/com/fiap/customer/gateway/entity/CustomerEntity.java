package com.fiap.customer.gateway.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List; // <- esse import estava faltando

@Entity
@Table(name = "customers")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cpf;

    private LocalDate birthDate;

    private String email;
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id") // cria a FK em AddressEntity
    private List<AddressEntity> addresses;
}
