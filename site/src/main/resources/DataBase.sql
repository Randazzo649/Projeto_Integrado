CREATE DATABASE IF NOT EXISTS UnitHub;
USE UnitHub;


CREATE TABLE IF NOT EXISTS Usuario(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45),
    email VARCHAR(45),
    senha VARCHAR(45),
    foto VARCHAR(100),
    curador BOOLEAN
);

CREATE TABLE IF NOT EXISTS Empresa(
    id BIGINT PRIMARY KEY AUTO_INCREMENT
    razao VARCHAR(45),
    cnpj VARCHAR(18),
    telefone VARCHAR(19),
    endereco VARCHAR(100),
    email VARCHAR(45),
    senha VARCHAR(45),
    foto VARCHAR(100),
    cor VARCHAR(16)
);

CREATE TABLE IF NOT EXISTS Empresa_has_Usuario(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    funcao VARCHAR(45) NOT NULL,
    id_usuario BIGINT NOT NULL,
    id_empresa BIGINT NOT NULL,

    FOREIGN KEY (id_usuario) REFERENCES Usuario(id),
    FOREIGN KEY (id_empresa) REFERENCES Empresa(id)
);