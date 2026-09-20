CREATE DATABASE IF NOT EXISTS UnitHub;
USE UnitHub;


CREATE TABLE IF NOT EXISTS Usuario(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    foto VARCHAR(100) NULL,
    curador BOOLEAN,
    ativo BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS Empresa(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    razao VARCHAR(200) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    telefone VARCHAR(19) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
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
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    data_decis DATETIME,
    data_solic DATETIME NOT NULL,
    razao VARCHAR(45) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    telefone VARCHAR(19) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    senha VARCHAR(128) NOT NULL,
    nome VARCHAR(45) NOT NULL,
    documento VARCHAR(250) NOT NULL
);


CREATE TABLE IF NOT EXISTS Funcionario(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(128) NOT NULL,
    cargo VARCHAR(45) NOT NULL,
    salario VARCHAR(45) NOT NULL,
    data_admissao DATE NOT NULL,
    atvio BOOLEAN,
    id_empresa BIGINT NOT NULL,
    FOREIGN KEY (id_empresa) REFERENCES Empresa(id)
);

CREATE TABLE IF NOT EXISTS Modulo(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    imagem VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS Empresa_has_modulo(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_empresa BIGINT NOT NULL,
	id_modulo BIGINT NOT NULL,
    FOREIGN KEY (id_empresa) REFERENCES Empresa(id),
    FOREIGN KEY (id_modulo) REFERENCES Modulo(id)
);

CREATE TABLE IF NOT EXISTS Solicitacao_has_modulo(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_solicitacao BIGINT NOT NULL,
	id_modulo BIGINT NOT NULL,
    FOREIGN KEY (id_solicitacao) REFERENCES Solicitacao(id),
    FOREIGN KEY (id_modulo) REFERENCES Modulo(id)
);

/*Modulo de Estoque*/
CREATE TABLE IF NOT EXISTS Estoque(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45),
    id_empresa BIGINT NOT NULL,
    FOREIGN KEY (id_empresa) REFERENCES Empresa(id)
);

CREATE TABLE IF NOT EXISTS RelatorioEstoque(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(45) NOT NULL,
    quantidade INT NOT NULL,
    data DATETIME,
    observacao TEXT
);

CREATE TABLE IF NOT EXISTS Produto(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    descricao VARCHAR(45) NOT NULL,
	tipo VARCHAR(45) NOT NULL,
    quantidade INT NOT NULL,
    categoria VARCHAR(45),
    material VARCHAR(45),
    tamanho VARCHAR(45),
    cor VARCHAR(45),
    id_estoque BIGINT NOT NULL,
    id_relatorio_estoque BIGINT NOT NULL,
    FOREIGN KEY (id_estoque) REFERENCES Estoque(id),
    FOREIGN KEY (id_relatorio_estoque) REFERENCES RelatorioEstoque(id)
);

CREATE TABLE IF NOT EXISTS ProdutoFotos(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    foto VARCHAR(45) NOT NULL,
    id_produto BIGINT NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES Produto(id)
);

/*Modulo de Venda*/
CREATE TABLE IF NOT EXISTS Cliente(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    telefone VARCHAR(45) NOT NULL,
    data_cadastro DATE,
    cep VARCHAR(45),
    numero VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS Venda(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    data_venda DATETIME NOT NULL,
    observacao TEXT,
    id_funcionario BIGINT NOT NULL,
    id_cliente BIGINT NOT NULL,
    FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id)
);

/*Modulo Financeiro*/
CREATE TABLE IF NOT EXISTS Movimentacao_Financeira(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    data DATETIME NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    para VARCHAR(45) NULL,
    observacao VARCHAR(45) NULL,
    id_empresa BIGINT NOT NULL,
    FOREIGN KEY (id_empresa) REFERENCES Empresa(id)
);

CREATE TABLE IF NOT EXISTS Conta(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(7),
    valor DECIMAL(10,2) NOT NULL,
    juros DECIMAL(10,2) NOT NULL,
    multa DECIMAL(10,2) NOT NULL,
    data_vencimento DATE NOT NULL,
    data_abertura DATE NOT NULL,
    sataus VARCHAR(6) NOT NULL,
    id_empresa BIGINT NOT NULL,
    id_mov_finan BIGINT NOT NULL,
    FOREIGN KEY (id_empresa) REFERENCES Empresa(id),
    FOREIGN KEY (id_mov_finan) REFERENCES Movimentacao_Financeira(id)
);

/* VIEWS */



/* PROCEDURES */

DELIMITER $$
CREATE PROCEDURE cadastrar_empresa(IN id_solicitacao BIGINT, IN razao_in VARCHAR(45), IN cnpj_in VARCHAR(18), IN nome_in VARCHAR(45), IN telefone_in VARCHAR(19), IN endereco_in VARCHAR(100), IN email_usuario VARCHAR(100), IN hash_senha VARCHAR(128)) 
BEGIN
	DECLARE usuario_id BIGINT; DECLARE empresa_id BIGINT;
	
	INSERT INTO Usuario(email, senha, curador) VALUES (email_usuario, hash_senha, 0);
	SET usuario_id = LAST_INSERT_ID();
    
	INSERT INTO Empresa(razao, cnpj, nome, telefone, endereco) VALUES (razao_in, cnpj_in, nome_in, telefone_in, endereco_in);
	SET empresa_id = LAST_INSERT_ID();
    
    INSERT INTO Empresa_has_usuario(id_usuario, id_empresa, funcao) VALUES (usuario_id, empresa_id, "adm");
    
    UPDATE Solicitacao SET data_decis = NOW() WHERE id = id_solicitacao;

END $$
DELIMITER ;


INSERT INTO Usuario(nome, email, senha, curador) VALUES ('admin', 'admin@gmail.com', "$argon2id$v=19$m=8192,t=2,p=1$pqgiLpo6XpIZXratcbjq8A$nQfl7qlD0qSiEHl6TPrmKv282L/XblC/8PIp7Z79hAw", 1);
INSERT INTO Modulo(nome, descricao, imagem) VALUES ("FINANCEIRO", "Controle todas as entradas, saídas e movimentações de dinheiro de sua empresa", "/imgs/receber.png");
INSERT INTO Modulo(nome, descricao, imagem) VALUES ("RECURSOS HUMANOS", "Gerencie com facilidade funcionários, cargos, férias, folhas de pagamento e benefícios", "/imgs/RH.png");
INSERT INTO Modulo(nome, descricao, imagem) VALUES ("COMERCIAL", "Administre seus clientes e gerencie suas vendas, cotações e contratos", "/imgs/carrinho.png");
INSERT INTO Modulo(nome, descricao, imagem) VALUES ("ESTOQUE", "Cadastre itens, categorize seu inventário e acompanhe a movimentação dos produtos", "/imgs/estoque.png");

