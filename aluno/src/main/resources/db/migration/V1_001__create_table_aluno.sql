create table TB_ALUNO
(
    id   bigint auto_increment not null primary key,
    nome varchar(100)        not null,
    rm   varchar(20)         not null,
    cpf  varchar(5)         not null
)
