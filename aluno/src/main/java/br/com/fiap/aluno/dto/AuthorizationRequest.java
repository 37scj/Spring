package br.com.fiap.aluno.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationRequest implements Serializable {

    private String rm;
    private String cpf;
    private String cardNumber;

}
