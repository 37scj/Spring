package br.com.fiap.aluno.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO implements Serializable {

    private Long id;
    private LocalDateTime data;
    private Double valor;
    private AlunoDTO aluno;

    public TransactionDTO(Long id, LocalDateTime data, Double valor) {
        this.id = id;
        this.data = data;
        this.valor = valor;
    }
}
