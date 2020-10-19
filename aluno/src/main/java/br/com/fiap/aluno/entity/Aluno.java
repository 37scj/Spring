package br.com.fiap.aluno.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.fiap.aluno.dto.AlunoDTO;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
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


	public Aluno() {
		// TODO Auto-generated constructor stub
	}

	public Aluno(String nome, String cpf, String rm) {
		super();
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



}
