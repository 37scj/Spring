drop table if exists tb_aluno;

create table tb_aluno
(
    id   bigint auto_increment not null primary key,
    nome varchar(100)          not null,
    rm   varchar(20)           not null,
    cpf  varchar(5)            not null
);

drop table if exists tb_transacao;

create table tb_transacao
(
    id       bigint auto_increment not null primary key,
    id_aluno bigint                not null,
    foreign key (id_aluno) references tb_aluno (id),
    data     datetime              not null,
    valor    numeric(20, 2)        not null
)
