package com.fiap.customer.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@EqualsAndHashCode(of = "cpf")
public class Customer {

    private Long id;
    private final String name;
    private final Cpf cpf;
    private final String email;
    private String phone;
    private final LocalDate birthDate;
    private final List<Address> addresses;

    public Customer(String name, Cpf cpf, String email, String phone, LocalDate birthDate, List<Address> addresses) {
        validate(name, cpf, email, birthDate, addresses);
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.addresses = addresses;
    }

    public Customer(Long id, String name, Cpf cpf, String email, String phone, LocalDate birthDate,
            List<Address> addresses) {
        this(name, cpf, email, phone, birthDate, addresses);
        this.id = id;
    }

    private void validate(String name, Cpf cpf, String email, LocalDate birthDate, List<Address> addresses) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be empty.");
        }
        if (cpf == null) {
            throw new IllegalArgumentException("CPF must not be null.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email must be valid.");
        }
        if (birthDate == null || birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date must be valid.");
        }
        if (addresses == null || addresses.isEmpty()) {
            throw new IllegalArgumentException("At least one address is required.");
        }
    }
}
