create database dbAutenticacao;
use dbAutenticacao;
create 	table usuario (
	idusuario int not null auto_increment primary key,
	login varchar(45),
	senha varchar(45),
	autorizacao int
);