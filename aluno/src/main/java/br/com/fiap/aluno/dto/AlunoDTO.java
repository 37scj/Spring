package br.com.fiap.aluno.dto;

import br.com.fiap.aluno.entity.Aluno;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlunoDTO implements Serializable {

    private static final long serialVersionUID = -1541134953490160695L;

    private Long id;
    private String nome;
    private String cpf;
    private String rm;

    public AlunoDTO(Aluno aluno) {
        if (aluno != null) {
            this.id = aluno.getId();
            this.nome = aluno.getNome();
            this.rm = aluno.getRm();
            this.cpf = aluno.getCpf();
        }
    }
}
