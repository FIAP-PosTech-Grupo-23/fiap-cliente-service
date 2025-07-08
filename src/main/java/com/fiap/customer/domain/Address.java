package com.fiap.customer.domain;

import lombok.Getter;
import java.util.Objects;

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
        if (isBlank(street))
            throw new IllegalArgumentException("Street must not be empty.");
        if (isBlank(number))
            throw new IllegalArgumentException("Number must not be empty.");
        if (isBlank(cep))
            throw new IllegalArgumentException("CEP must not be empty.");
        if (isBlank(city))
            throw new IllegalArgumentException("City must not be empty.");
        if (isBlank(state))
            throw new IllegalArgumentException("State must not be empty.");
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Address address = (Address) o;
        return street.equals(address.street) &&
                number.equals(address.number) &&
                cep.equals(address.cep) &&
                city.equals(address.city) &&
                state.equals(address.state) &&
                Objects.equals(complement, address.complement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, cep, city, state, complement);
    }
}
