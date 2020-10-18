drop table if exists TB_ALUNO;

create table TB_ALUNO(
  nome varchar(255) not null,
  rm varchar(255) not null,
  cpf varchar(255) not null
)