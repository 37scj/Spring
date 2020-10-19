# Projeto Credito Aluno  
  
 
 
# Como utilizar

  # Docker
  
    - Na raiz do projeto execute o arquivo docker-compose.yml
        - [COMANDO] docker-compose up
        
    - Se preferir pode criar schema e tabela na mao (tb_aluno)
    
          create table TB_ALUNO
          (
              id   bigint auto_increment not null primary key,
              nome varchar(100)          not null,
              rm   varchar(20)           not null,
              cpf  varchar(5)            not null
          );
  
  # MYSQL 
  
    - porta 3306
    - Portal web mysql : http://localhost:8081/?server=db&username=root
    
  # Swagger V3
    - http://localhost:8081/swagger-ui/index.html



