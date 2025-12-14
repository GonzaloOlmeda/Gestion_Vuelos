-- Schema para la gestión de vuelos

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

CREATE INDEX idx_origen ON vuelos(origen);
CREATE INDEX idx_destino ON vuelos(destino);
CREATE INDEX idx_compania ON vuelos(compania);