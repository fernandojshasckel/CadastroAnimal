CREATE DATABASE trabalho_g1_fernando;

CREATE TABLE trabalho_g1_fernando.vacina (
	id INT PRIMARY KEY AUTO_INCREMENT,
    vacina VARCHAR(100) NOT NULL,
    marca VARCHAR(100) NOT NULL,
    ml DOUBLE NOT NULL,
    bula VARCHAR(500) NOT NULL,
    preco DOUBLE NOT NULL
);

CREATE TABLE trabalho_g1_fernando.tipoanimal (
	id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(100) NOT NULL
);

CREATE TABLE trabalho_g1_fernando.raca (
	id INT PRIMARY KEY AUTO_INCREMENT,
    raca VARCHAR(100) NOT NULL,
    cor VARCHAR(50) NOT NULL
);

CREATE TABLE trabalho_g1_fernando.estado (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    sigla VARCHAR(2) NOT NULL
);

CREATE TABLE trabalho_g1_fernando.cidade (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    id_estado INT NOT NULL,
    FOREIGN KEY (id_estado) REFERENCES estado(id)
);

CREATE TABLE trabalho_g1_fernando.animal (
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal VARCHAR(100) NOT NULL,
    id_raca INT NOT NULL,
	peso FLOAT NOT NULL,
    tamanho FLOAT NOT NULL,
    vacinado BOOLEAN NOT NULL,
    estimativavida INT NOT NULL,
    id_nacionalidadeestado INT NOT NULL,
    id_nacionalidadecidade INT NOT NULL,
    id_tipoanimal INT NOT NULL,
    FOREIGN KEY (id_raca) REFERENCES raca(id),
    FOREIGN KEY (id_nacionalidadeestado) REFERENCES estado(id),
    FOREIGN KEY (id_nacionalidadecidade) REFERENCES cidade(id),
    FOREIGN KEY (id_tipoanimal) REFERENCES tipoanimal(id)
);

CREATE TABLE trabalho_g1_fernando.vacinaanimal (
	id INT PRIMARY KEY NOT NULL,
    id_animal INT NOT NULL,
    id_vacina INT NOT NULL,
    FOREIGN KEY (id_animal) REFERENCES animal(id),
    FOREIGN KEY (id_vacina) REFERENCES vacina(id)
)