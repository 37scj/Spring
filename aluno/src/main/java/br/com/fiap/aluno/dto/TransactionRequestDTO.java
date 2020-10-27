package br.com.fiap.aluno.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TransactionRequestDTO implements Serializable {

    
	private Long id;
	private String rm;
    private String cpf;
    private Double value;

    
}
