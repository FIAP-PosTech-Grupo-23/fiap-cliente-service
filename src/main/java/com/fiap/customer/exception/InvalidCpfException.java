package com.fiap.customer.exception;

public class InvalidCpfException extends RuntimeException {
    public InvalidCpfException(String cpf) {
        super("Invalid CPF: " + cpf);
    }
}
