drop table if exists TB_ALUNO;

create table TB_ALUNO(
  id long auto_increment not null primary key,
  nome varchar(255) not null,
  rm varchar(255) not null,
  cpf varchar(255) not null
)
