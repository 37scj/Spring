package br.com.fiap.aluno.entity;

import javax.persistence.*;

import br.com.fiap.aluno.dto.AlunoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
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

	public Aluno(AlunoDTO alunoDTO) {
		if (alunoDTO != null) {
			this.setNome(alunoDTO.getNome());
			this.setRm(alunoDTO.getRm());
			this.setCpf(alunoDTO.getCpf());
		}
	}

}
