package br.com.fiap.aluno.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.fiap.aluno.dto.AlunoDTO;

@Entity
@Table(name = "TB_ALUNO")
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

	public Aluno(AlunoDTO saveAlunoDTO) {
		if (saveAlunoDTO != null) {
			this.setId(saveAlunoDTO.getId());
			this.setNome(saveAlunoDTO.getNome());
			this.setRm(saveAlunoDTO.getRm());
			this.setCpf(saveAlunoDTO.getCpf());
		}
	}

	public Aluno(String nome, String cpf, String rm) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.rm = rm;
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
