package br.com.fiap.aluno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.aluno.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
