package com.fiap.cliente.domain;

import java.util.Objects;
import com.fiap.cliente.exception.InvalidCpfException;

public class Cpf {

    private final String value;

    public Cpf(String value) {
        if (!isValid(value)) {
            throw new InvalidCpfException(value);
        }
        this.value = normalize(value);
    }

    public String getValue() {
        return value;
    }

    private String normalize(String cpf) {
        return cpf.replaceAll("[^\\d]", ""); // remove pontos e traços
    }

    private boolean isValid(String cpf) {
        if (cpf == null)
            return false;

        cpf = normalize(cpf);

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false; // tamanho inválido ou todos os dígitos iguais
        }

        try {
            int d1 = 0, d2 = 0;
            for (int i = 0; i < 9; i++) {
                int digit = Character.getNumericValue(cpf.charAt(i));
                d1 += digit * (10 - i);
                d2 += digit * (11 - i);
            }

            d1 = 11 - (d1 % 11);
            if (d1 >= 10)
                d1 = 0;

            d2 += d1 * 2;
            d2 = 11 - (d2 % 11);
            if (d2 >= 10)
                d2 = 0;

            return d1 == Character.getNumericValue(cpf.charAt(9)) &&
                    d2 == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Cpf))
            return false;
        Cpf cpf = (Cpf) o;
        return value.equals(cpf.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
