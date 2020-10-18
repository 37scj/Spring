package br.com.fiap.aluno.dto;

import java.io.Serializable;

import br.com.fiap.aluno.model.Aluno;

public class AlunoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1541134953490160695L;

	private Long id;

	private String nome;

	private String cpf;

	private String rm;
	
	
	 public AlunoDTO(Aluno saveAluno) {
	        if (saveAluno != null) {
	            this.setId(saveAluno.getId());
	            this.setNome(saveAluno.getNome());
	            this.setRm(saveAluno.getRm());
	            this.setCpf(saveAluno.getCpf());
	        }
	    }

	public AlunoDTO(Long id, String nome, String cpf, String rm) {
		super();
		this.id = id;
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
