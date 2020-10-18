package br.com.fiap.batch.model;

/**
 * Dados a serem representados
 * do arquivo lista_alunos.txt
 * 
 * @author lucasrodriguesdonascimento
 *
 */
public class AlunoDTO {
	
	private String nome;
	private Integer rm;
	private String cpf;
	
	public AlunoDTO() {
	}
	
	public AlunoDTO(String nome, Integer rm, String cpf) {
		super();
		this.nome = nome;
		this.rm = rm;
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getRm() {
		return rm;
	}
	public void setRm(Integer rm) {
		this.rm = rm;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	

}
