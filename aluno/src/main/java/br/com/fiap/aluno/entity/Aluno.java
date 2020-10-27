package br.com.fiap.aluno.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.fiap.aluno.dto.AlunoDTO;

@Entity
@Table(name = "tb_aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private String cpf;

    @Column
    private String rm;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aluno")
    private Set<Transaction> transactions;


	public Aluno() {
	}


    public Aluno(Long id, String nome, String cpf, String rm, Set<Transaction> transactions) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.rm = rm;
		this.transactions = transactions;
	}
    
    public Aluno(String nome, String cpf, String rm) {
        this.nome = nome;
        this.cpf = cpf;
        this.rm = rm;
    }
   

	public Aluno(AlunoDTO alunoDTO) {
        if (alunoDTO != null) {
            this.setNome(alunoDTO.getNome());
            this.setRm(alunoDTO.getRm());
            this.setCpf(alunoDTO.getCpf());
        }
    }


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getRm() {
		return rm;
	}



	public void setRm(String rm) {
		this.rm = rm;
	}



	public Set<Transaction> getTransactions() {
		return transactions;
	}



	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	

}
