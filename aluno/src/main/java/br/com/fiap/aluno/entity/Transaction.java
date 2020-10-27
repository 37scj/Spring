package br.com.fiap.aluno.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    
    

	public Transaction(Long id, Aluno aluno, Double valor, LocalDateTime data) {
		super();
		this.id = id;
		this.aluno = aluno;
		this.valor = valor;
		this.data = data;
	}



}
