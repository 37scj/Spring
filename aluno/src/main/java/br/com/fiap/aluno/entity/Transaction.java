package br.com.fiap.aluno.entity;

import br.com.fiap.aluno.dto.AlunoDTO;
import br.com.fiap.aluno.dto.TransactionDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_transacao")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(columnDefinition = "ID_ALUNO")
    private Aluno aluno;

    @Column
    private Double valor;

    @Column
    private LocalDateTime data;

    public TransactionDTO toModel() {
        return new TransactionDTO(getId(), getData(), getValor(), new AlunoDTO(getAluno()));
    }
    public TransactionDTO toModelWithoutAluno() {
        return new TransactionDTO(getId(), getData(), getValor());
    }

}
