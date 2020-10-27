package br.com.fiap.aluno.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Gabriel Ribeiro
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TransactionRequest implements Serializable {

    private String rm;
    private String cpf;
    private Double value;
}
