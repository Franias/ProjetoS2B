drop database gabinete;
create database gabinete;
/*Banco de dados base para o projeto final*/
use gabinete;

create table setores (
    id int auto_increment not null,
    nome varchar(200),
    id_setor_pai int,
    primary key (id)
);

create table perfil (
    id int auto_increment,
    nome varchar(50) not null,
    descricao text,
    situacao varchar(10) not null,
    primary key (id)
);

insert into perfil values(null,'servidor','servidor','pendente');
insert into perfil values(null,'gabinete','gabinete','pendente');
insert into perfil values(null,'outros','outros','pendente');

create table usuarios (
    cpf varchar(11) not null,
    siape int,
    nome varchar(200) not null,
    sexo varchar(1),
    endereco text,
    email varchar(255),
    telefone varchar(25),
    username varchar(50),
    senha varchar(255),
    situacao varchar(1),
    observacoes text,
    id_setor int,
    id_perfil int,
    primary key (cpf),
    foreign key (id_setor) references setores(id),
    foreign key (id_perfil) references perfil(id)
);

create table solicitacao_documentos
(
  id_solicitacao int not null auto_increment,
  objetivo varchar(255),
  data_solicitacao datetime,
  data_documento datetime,
  conteudo_documento text,
  situacao varchar(20),
  anexos varchar(255),
  cpf_servidor varchar(11),
  primary key (id_solicitacao),
  foreign key (cpf_servidor) references usuarios(cpf)
);

create table documentos_oficiais
(
  numero int not null,
  ano int not null,
  tipo varchar(10) not null,
  data_emissao date,
  conteudo text,
  anexos varchar(255),
  cpf_emissor varchar(11),
  primary key (numero, ano, tipo),
  foreign key (cpf_emissor) references usuarios(cpf)
);

insert into setores values (-1, 'Nenhum', -1);

/*usuário para testes */
insert into usuarios (cpf,siape,sexo,nome,username,email,senha,id_perfil) 
values ('02384516906',12345489,'F','eduarda','eduarda','eduarda@gmail.com',MD5('1234'),1);

insert into usuarios (cpf,siape,sexo,nome,username,email,senha,id_perfil) 
values ('92154876030',12845498,'F','francielli','francielli','fran@gmail.com',MD5('1234'),2);

insert into usuarios (cpf,siape,sexo,nome,username,email,senha,id_perfil) 
values ('32407168950',14445408,'M','andre','andre','andre@gmail.com',MD5('1234'),3);

