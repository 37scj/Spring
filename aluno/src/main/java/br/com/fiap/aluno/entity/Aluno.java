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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_aluno")
@Getter @Setter @AllArgsConstructor
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

}
