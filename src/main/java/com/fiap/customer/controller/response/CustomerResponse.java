package com.fiap.customer.controller.response;

import java.time.LocalDate;
import java.util.List;

public record CustomerResponse(
        Long id,
        String name,
        String cpf,
        String email,
        String phone,
        LocalDate birthDate,
        List<AddressResponse> addresses) {
}
