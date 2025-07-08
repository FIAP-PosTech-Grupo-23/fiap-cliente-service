package com.fiap.customer.controller.request;

import java.time.LocalDate;
import java.util.List;

public record CustomerRequest(
    String name,
    String cpf,
    String email,
    String phone,
    LocalDate birthDate,
    List<AddressRequest> addresses
) {}
