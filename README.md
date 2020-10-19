# Projeto Credito Aluno  
    
A FIAP resolveu criar seu próprio cartão de crédito para ser utilizado pelos alunos e para isso necessita de um sistema para gerenciamento e integração com outras empresas. Desenvolva este sistema com os seguintes requisitos:

    RF1 - Cadastro de Alunos
    RF2 - O cadastro inicial dos potenciais clientes do cartão será realizado via integração com um arquivo .txt disponibilizado https://drive.google.com/open?id=19ILqrYjOEe4C840ZRwhKDauvhDZCKcWa 
    RF3 - As compras realizadas nos cartões dos clientes serão recebidas via integração com uma Autorizadora. Criar os endpoints necessários para receber as realizações de transações.
    RF4 - Deve ser possível gerar um extrato via download (endpoint) ou enviado no email do cliente (pode escolher uma opção).

    RNF1 - Utilizar o Spring Framework.
    RNF2 - Criar um arquivo readme.md com as instruções para subir o/os projeto/s.
    RNF3 - Criar testes unitários e integrados para o/os projeto/s.
    RNF4 - Gerar uma massa simulada de transações.
    RNF5 - Documentação Swagger
    
    
    1 - Execute o modulo Batch para carregar a massa de dados
    2 - Acesse a documentacao para ver os endpoints.
    
  # Docker:
  
    - Na raiz do projeto execute o arquivo docker-compose.yml
        - [COMANDO] docker-compose up
        
    - Se preferir pode criar schema e tabela manualmente (tb_aluno)
    
          create table TB_ALUNO
          (
              id   bigint auto_increment not null primary key,
              nome varchar(100)          not null,
              rm   varchar(20)           not null,
              cpf  varchar(5)            not null
          );
  
  # MySql:
  
    - porta 3306
    - Schema : credalunos_db
    - Tabela : tb_aluno
    - Portal web mysql : http://localhost:8081/?server=db
    
  # Documentação:
    - http://localhost:8081/swagger-ui/index.html



