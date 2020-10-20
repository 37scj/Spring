package br.com.fiap.aluno.dto;

import br.com.fiap.aluno.entity.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Gabriel Ribeiro
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TransactionDTO implements Serializable {

    private Long id;
    private LocalDateTime data;
    private Double valor;
    private AlunoDTO aluno;

    public TransactionDTO(Transaction t) {
        if (t != null) {
            setId(t.getId());
            setData(t.getData());
            setValor(t.getValor());
            setAluno(new AlunoDTO(t.getAluno()));
        }
    }

}
