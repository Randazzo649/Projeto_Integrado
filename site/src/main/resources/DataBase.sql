CREATE DATABASE IF NOT EXISTS UnitHub;
USE UnitHub;


CREATE TABLE IF NOT EXISTS Usuario(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    foto VARCHAR(100) NULL,
    curador BOOLEAN
);

CREATE TABLE IF NOT EXISTS Empresa(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    razao VARCHAR(45) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    telefone VARCHAR(19) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    foto VARCHAR(100) NULL,
    cor VARCHAR(16) NULL,
    nome VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS Empresa_has_Usuario(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    funcao VARCHAR(45) NOT NULL,
    id_usuario BIGINT NOT NULL,
    id_empresa BIGINT NOT NULL,

    FOREIGN KEY (id_usuario) REFERENCES Usuario(id),
    FOREIGN KEY (id_empresa) REFERENCES Empresa(id)
);


CREATE TABLE IF NOT EXISTS Solicitacao(
    id INT PRIMARY KEY AUTO_INCREMENT,
    data_decis DATETIME,
    data_solic DATETIME NOT NULL,
    razao VARCHAR(45) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    telefone VARCHAR(19) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    nome VARCHAR(45) NOT NULL,
    documento VARCHAR(100) NOT NULL
);

INSERT INTO Usuario(nome, email, senha, curador) VALUES ('admin', 'admin@gmail.com', "$argon2id$v=19$m=8192,t=2,p=1$pqgiLpo6XpIZXratcbjq8A$nQfl7qlD0qSiEHl6TPrmKv282L/XblC/8PIp7Z79hAw", 1)
