package br.com.fiap.aluno.entity;

import br.com.fiap.aluno.dto.AlunoDTO;
import br.com.fiap.aluno.dto.TransactionDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Gabriel Ribeiro
 */
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
    @JoinColumn(name = "ID_ALUNO")
    private Aluno aluno;

    @Column
    private Double valor;

    @Column
    private LocalDateTime data;

}
