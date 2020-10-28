# Projeto Credito Aluno  

## Objetivo do projeto 

#### Dado que:
A FIAP resolveu criar seu próprio cartão de crédito para ser utilizado pelos alunos e para isso necessita de um sistema para gerenciamento e integração com outras empresas. Desenvolva este sistema com os seguintes requisitos:

    RF1 - Cadastro de Alunos
    RF2 - O cadastro inicial dos potenciais clientes do cartão será realizado via integração com um arquivo .txt disponibilizado https://drive.google.com/open?id=19ILqrYjOEe4C840ZRwhKDauvhDZCKcWa 
    RF3 - As compras realizadas nos cartões dos clientes serão recebidas via integração com uma Autorizadora. Criar os endpoints necessários para receber as realizações de transações.
    RF4 - Deve ser possível gerar um extrato via download (endpoint) ou enviado no email do cliente (pode escolher uma opção).

####
    RNF1 - Utilizar o Spring Framework.
    RNF2 - Criar um arquivo readme.md com as instruções para subir o/os projeto/s.
    RNF3 - Criar testes unitários e integrados para o/os projeto/s.
    RNF4 - Gerar uma massa simulada de transações.
    RNF5 - Documentação Swagger

      
## Instrução

1. Instale todas as dependencias do projeto
2. Execute o modulo Batch para carregar a massa de dados
3. Acesse a documentacao para ver os endpoints.

## Preparação

### Docker:

- Na raiz do projeto execute o arquivo docker-compose.yml
 
```shell script
docker-compose up
```

### MySql:
    - porta 3306
    - Schema: credalunos_db
    - DB user: ucredalunos
    - Portal web mysql : http://localhost:8081/?server=credalunos_db

Não é necessario rodar script no banco. As tabelas serão criadas conforme scripts do migration (flyway).
Ou se preferir pode criar schema e tabela manualmente (tb_aluno)

```mysql
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
```

## Execução dos projetos java

Ambos os projetos utilizam da mesma base de dados mysql, mas somente o projeto `aluno` cria as tabelas necessárias.

### Spring API
O projeto aluno tem a API para cadastro do aluno e recebe as transações via integração
 
Entrar no diretório `batch` e executar o comando abaixo para importar o arquivo 
 
```shell script
mvn spring-boot:run 
```
 
### Spring Batch
O projeto batch executa uma leitura de um arquivo de dados, configurado no properties `file.chunk`.

Entrar no diretório `batch` e executar o comando abaixo para importar o arquivo chunk para a base de dados.

 **Atenção:** os dados não serão duplicados pois o numero de RM é unico.

```shell script
mvn spring-boot:run 
```

#### Documentação API:
  
    Swagger Open API 3.0
    - http://localhost:8080/swagger-ui/index.html

