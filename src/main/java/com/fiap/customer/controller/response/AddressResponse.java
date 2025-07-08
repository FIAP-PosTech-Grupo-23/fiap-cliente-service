package com.fiap.customer.controller.response;

public record AddressResponse(
    String street,
    String number,
    String cep,
    String city,
    String state,
    String complement
) {}
