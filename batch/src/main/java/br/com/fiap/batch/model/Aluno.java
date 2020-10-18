package br.com.fiap.batch.model;

/**
 * Dados a serem representados
 * do arquivo lista_alunos.txt
 * 
 * @author lucasrodriguesdonascimento
 *
 */
public class Aluno {
	
	private String nome;
	private String rm;
	private String cpf;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRm() {
		return rm;
	}
	public void setRm(String rm) {
		this.rm = rm;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	@Override
	public String toString() {
		return "Aluno {"
				+ "nome'" + nome+ "'"
				+ "rm='" + rm + "'"
				+ "cpf='" + cpf + "'"
				+ "}";
	}
	
	
	

}
