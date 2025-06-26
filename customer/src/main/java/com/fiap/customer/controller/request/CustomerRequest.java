package com.fiap.customer.controller.request;

import java.time.LocalDate;

public record CustomerRequest(
        String name,
        String cpf,
        String email,
        String phone,
        LocalDate birthDate

) {
}
