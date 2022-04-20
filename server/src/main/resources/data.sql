INSERT INTO Client (first_name, last_name) VALUES ('Mikhail', 'Sivakov');
INSERT INTO Card (client_id, card_number, pin, balance, currency)
VALUES
    (1, 1111222233330003, 3333, '100000.00', 'RUB'),
    (1, 1111222233330014, 3333, '10000.00', 'USD');

INSERT INTO Client (first_name, last_name) VALUES ('Andrey', 'Malyh');
INSERT INTO Card (client_id, card_number, pin, balance, currency)
VALUES
    (2, 1111222233330002, 2222, '50000.00', 'RUB'),
    (2, 1111222233330022, 2222, '5000.00', 'USD');
