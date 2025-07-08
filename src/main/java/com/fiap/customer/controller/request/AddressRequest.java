package com.fiap.customer.controller.request;

public record AddressRequest(
        String street,
        String number,
        String cep,
        String city,
        String state,
        String complement) {
}
