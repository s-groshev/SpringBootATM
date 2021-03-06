DROP TABLE IF EXISTS Card;
DROP TABLE IF EXISTS Client;

CREATE TABLE Client(
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL
);

CREATE TABLE Card (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_id BIGINT NOT NULL,
    card_number BIGINT CHECK (CARD_NUMBER  > 0),
    pin INT NOT NULL,
    balance DECIMAL(20, 2) NOT NULL DEFAULT 0.00,
    currency VARCHAR(3) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES Client(id)
);