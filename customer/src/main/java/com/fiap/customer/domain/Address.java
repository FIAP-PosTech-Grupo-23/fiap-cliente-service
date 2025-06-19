package com.fiap.customer.domain;

import lombok.Getter;

@Getter
public class Address {

    private final String street;
    private final String number;
    private final String cep;
    private final String city;
    private final String state;
    private final String complement;

    public Address(String street, String number, String cep, String city, String state, String complement) {
        validate(street, number, cep, city, state);
        this.street = street;
        this.number = number;
        this.cep = cep;
        this.city = city;
        this.state = state;
        this.complement = complement;
    }

    private void validate(String street, String number, String cep, String city, String state) {
        if (street == null || street.isBlank()) throw new IllegalArgumentException("Street must not be empty.");
        if (number == null || number.isBlank()) throw new IllegalArgumentException("Number must not be empty.");
        if (cep == null || cep.isBlank()) throw new IllegalArgumentException("CEP must not be empty.");
        if (city == null || city.isBlank()) throw new IllegalArgumentException("City must not be empty.");
        if (state == null || state.isBlank()) throw new IllegalArgumentException("State must not be empty.");
    }
}
