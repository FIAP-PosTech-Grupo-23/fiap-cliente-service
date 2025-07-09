package com.fiap.cliente.domain;

import lombok.Getter;

import java.util.Objects;

import com.fiap.cliente.exception.ValidationException;

@Getter

public class Endereco {
    private final String rua;
    private final String numero;
    private final String cep;
    private final String cidade;
    private final String estado;
    private final String complemento;

    public Endereco(String rua, String numero, String cep, String cidade, String estado, String complemento) {
        validar(rua, numero, cep, cidade, estado);
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
    }

    private void validar(String rua, String numero, String cep, String cidade, String estado) {
        if (isBlank(rua))
            throw new ValidationException("Rua não pode ser vazia.");
        if (isBlank(numero))
            throw new ValidationException("Número não pode ser vazio.");
        if (isBlank(cep))
            throw new ValidationException("CEP não pode ser vazio.");
        if (isBlank(cidade))
            throw new ValidationException("Cidade não pode ser vazia.");
        if (isBlank(estado))
            throw new ValidationException("Estado não pode ser vazio.");
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

        Endereco address = (Endereco) o;
        return rua.equals(address.rua) &&
                numero.equals(address.numero) &&
                cep.equals(address.cep) &&
                cidade.equals(address.cidade) &&
                estado.equals(address.estado) &&
                Objects.equals(complemento, address.complemento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rua, numero, cep, cidade, estado, complemento);
    }
}
