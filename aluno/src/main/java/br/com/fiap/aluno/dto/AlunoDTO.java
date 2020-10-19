package br.com.fiap.aluno.dto;

import br.com.fiap.aluno.model.Aluno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO implements Serializable {

    private static final long serialVersionUID = -1541134953490160695L;

    private Long id;
    private String nome;
    private String cpf;
    private String rm;

    public AlunoDTO(Aluno saveAluno) {
        if (saveAluno != null) {
            this.id = saveAluno.getId();
            this.nome = saveAluno.getNome();
            this.rm = saveAluno.getRm();
            this.cpf = saveAluno.getCpf();
        }
    }

}
