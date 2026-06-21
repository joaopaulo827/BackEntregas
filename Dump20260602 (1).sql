create database db_gerenciador;
use db_gerenciador;
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role ENUM('ADMIN','OPERADOR','MOTORISTA','CLIENTE','SUPERVISOR') NOT NULL
);
INSERT INTO usuarios 
VALUES (1,'Admin System','admin@transportadora.com','123456','ADMIN'),
       (2,'Operation Solutions','operation@transportadora.com','205689','OPERADOR'),
       (3,'Driver Road','driver@transportadora.com','325690','MOTORISTA'),
       (4,'Supervisor Aware','visor@transportadora.com','586410','SUPERVISOR'),
       (5,'Client Test','client@test.com','386324','CLIENTE');
       
CREATE TABLE motoristas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT,
    nome VARCHAR(150) NOT NULL,
    status ENUM('ATIVO','INATIVO') DEFAULT 'ATIVO',

    FOREIGN KEY (usuario_id)
    REFERENCES usuarios(id)
);
INSERT INTO motoristas VALUES
(1, 3, 'Driver Road', 'ATIVO');   
