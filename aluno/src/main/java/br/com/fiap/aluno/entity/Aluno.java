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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public Aluno(Long id, String nome, String cpf, String rm) {
        this.id = id;
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
