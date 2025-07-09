package com.fiap.cliente.exception;

import java.util.UUID;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(UUID uid) {
        super("Cliente com ID " + uid + " não encontrado.");
    }
}
