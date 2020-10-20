package br.com.fiap.aluno.repository;

import br.com.fiap.aluno.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByRm(String rm);
}
