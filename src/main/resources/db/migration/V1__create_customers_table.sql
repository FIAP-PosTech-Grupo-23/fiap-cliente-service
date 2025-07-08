CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    birth_date DATE NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(20)
);

CREATE TABLE address_entity (
    id SERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES customers(id) ON DELETE CASCADE,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(20) NOT NULL,
    cep VARCHAR(20) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    complement VARCHAR(255)
);
