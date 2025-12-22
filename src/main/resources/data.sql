
-- Datos iniciales para la tabla de vuelos

INSERT INTO vuelos (origen, destino, precio, numero_escalas, compania) VALUES
                                                                           ('Madrid', 'Barcelona', 89.99, 0, 'Iberia'),
                                                                           ('Madrid', 'París', 145.50, 0, 'Air France'),
                                                                           ('Barcelona', 'Londres', 120.00, 0, 'Vueling'),
                                                                           ('Madrid', 'Nueva York', 450.00, 1, 'Iberia'),
                                                                           ('Barcelona', 'Roma', 95.75, 0, 'Ryanair'),
                                                                           ('Madrid', 'Berlín', 110.00, 1, 'Lufthansa'),
                                                                           ('Valencia', 'Ámsterdam', 130.00, 0, 'KLM'),
                                                                           ('Sevilla', 'París', 125.50, 1, 'Air Europa'),
                                                                           ('Bilbao', 'Londres', 105.00, 0, 'British Airways'),
                                                                           ('Madrid', 'Tokio', 850.00, 2, 'Japan Airlines'),
                                                                           ('Barcelona', 'Dublín', 85.00, 0, 'Ryanair'),
                                                                           ('Madrid', 'Miami', 520.00, 1, 'American Airlines'),
                                                                           ('Málaga', 'Bruselas', 98.00, 0, 'Brussels Airlines'),
                                                                           ('Madrid', 'Lisboa', 75.00, 0, 'TAP Portugal'),
                                                                           ('Barcelona', 'Múnich', 115.00, 0, 'Lufthansa');



-- Datos iniciales para usuarios
-- Contraseña para admin: admin123
-- Contraseña para user1 y user2: user123

INSERT INTO usuarios (username, email, password, rol) VALUES
                                                          ('admin', 'admin@vuelos.com', '$2a$10$.s3wAPM7uN0H1sRLWs7/Iuo/inSzBPpfuo0U1JoWbr.nFRFFvykzm', 'ADMIN'),
                                                          ('user1', 'user1@email.com', '$2a$10$btc9xXq8Q35DIpvUHOfHm.BFd0qMYMqBV62PjxvOKg9pKB//B1JaC', 'USER'),
                                                          ('user2', 'user2@email.com', '$2a$10$btc9xXq8Q35DIpvUHOfHm.BFd0qMYMqBV62PjxvOKg9pKB//B1JaC', 'USER');
