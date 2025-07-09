CREATE TABLE enderecos (
    id BIGSERIAL PRIMARY KEY,
    rua VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(50),
    cep VARCHAR(15) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cliente_uid UUID NOT NULL REFERENCES clientes(uid) ON DELETE CASCADE
);
