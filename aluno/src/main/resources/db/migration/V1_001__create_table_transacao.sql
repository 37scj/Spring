create table tb_transacao
(
    id       bigint auto_increment not null primary key,
    id_aluno bigint                not null,
    foreign key (id_aluno) references tb_aluno (id),
    data     datetime              not null,
    valor    numeric(20, 2)        not null
)
