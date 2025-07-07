package com.fiap.customer.controller.response;

import java.time.LocalDate;

public record CustomerResponse(
    Long id,
    String name,
    String cpf,
    String email,
    String phone,
    LocalDate birthDate
) {}
