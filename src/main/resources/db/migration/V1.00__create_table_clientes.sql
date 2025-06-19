CREATE TABLE clientes
(
    id        BIGSERIAL PRIMARY KEY,
    nome      VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    login     VARCHAR(255) NOT NULL,
    senha     VARCHAR(255) NOT NULL,
    endereco  VARCHAR(255) NOT NULL,
    cpf       VARCHAR(14) NOT NULL UNIQUE
);
