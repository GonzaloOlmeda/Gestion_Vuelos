-- Schema para la gestión de vuelos

DROP TABLE IF EXISTS tokens;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS vuelos;

CREATE TABLE vuelos (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        origen VARCHAR(100) NOT NULL,
                        destino VARCHAR(100) NOT NULL,
                        precio DOUBLE NOT NULL,
                        numero_escalas INT NOT NULL DEFAULT 0,
                        compania VARCHAR(100) NOT NULL,
                        CONSTRAINT chk_precio CHECK (precio >= 0),
                        CONSTRAINT chk_escalas CHECK (numero_escalas >= 0)
);

CREATE TABLE usuarios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(50) NOT NULL UNIQUE,
                          email VARCHAR(100) NOT NULL UNIQUE,
                          password VARCHAR(255) NOT NULL,
                          rol VARCHAR(20) NOT NULL DEFAULT 'USER',
                          CONSTRAINT chk_rol CHECK (rol IN ('USER', 'ADMIN'))
);

CREATE TABLE tokens (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        token VARCHAR(500) NOT NULL UNIQUE,
                        tipo VARCHAR(20) NOT NULL DEFAULT 'BEARER',
                        revocado BOOLEAN NOT NULL DEFAULT FALSE,
                        expirado BOOLEAN NOT NULL DEFAULT FALSE,
                        usuario_id BIGINT NOT NULL,
                        CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE INDEX idx_origen ON vuelos(origen);
CREATE INDEX idx_destino ON vuelos(destino);
CREATE INDEX idx_compania ON vuelos(compania);
CREATE INDEX idx_username ON usuarios(username);
CREATE INDEX idx_email ON usuarios(email);
CREATE INDEX idx_token ON tokens(token);
CREATE INDEX idx_usuario_token ON tokens(usuario_id);
