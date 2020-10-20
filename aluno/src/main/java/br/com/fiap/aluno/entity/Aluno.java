package br.com.fiap.aluno.entity;

import br.com.fiap.aluno.dto.AlunoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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
