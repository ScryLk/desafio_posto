CREATE DATABASE IF NOT EXISTS desafio_posto
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE desafio_posto;

CREATE TABLE IF NOT EXISTS tipo_combustivel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE,
    preco_litro DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT chk_preco_positivo CHECK (preco_litro > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS bomba (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE,
    tipo_combustivel_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_bomba_tipo_combustivel
        FOREIGN KEY (tipo_combustivel_id)
        REFERENCES tipo_combustivel(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS abastecimento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bomba_id INT NOT NULL,
    data_abastecimento TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    litros DECIMAL(10, 3) NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    preco_litro_praticado DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_abastecimento_bomba
        FOREIGN KEY (bomba_id)
        REFERENCES bomba(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    CONSTRAINT chk_litros_positivo CHECK (litros > 0),
    CONSTRAINT chk_valor_total_positivo CHECK (valor_total > 0),
    CONSTRAINT chk_preco_litro_praticado_positivo CHECK (preco_litro_praticado > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_bomba_tipo_combustivel ON bomba(tipo_combustivel_id);
CREATE INDEX idx_abastecimento_bomba ON abastecimento(bomba_id);
CREATE INDEX idx_abastecimento_data ON abastecimento(data_abastecimento);

SELECT 'Tabelas criadas com sucesso!' AS Status;
SELECT COUNT(*) AS 'Tipos de Combustível' FROM tipo_combustivel;
SELECT COUNT(*) AS 'Bombas' FROM bomba;
SELECT COUNT(*) AS 'Abastecimentos' FROM abastecimento;
