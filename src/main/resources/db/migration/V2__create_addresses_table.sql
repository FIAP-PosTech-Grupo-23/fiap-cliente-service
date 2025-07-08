CREATE TABLE addresses (
    id BIGSERIAL PRIMARY KEY,
    street VARCHAR(100),
    number VARCHAR(10),
    complement VARCHAR(50),
    cep VARCHAR(15),
    city VARCHAR(50),
    state VARCHAR(2),
    customer_id BIGINT NOT NULL REFERENCES customers(id) ON DELETE CASCADE
);
