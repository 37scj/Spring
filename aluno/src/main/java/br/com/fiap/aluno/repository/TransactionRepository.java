package br.com.fiap.aluno.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.aluno.entity.Aluno;
import br.com.fiap.aluno.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query(value = "select new br.com.fiap.aluno.entity.Aluno (alunos.nome , alunos.cpf , alunos.rm) from Aluno alunos where alunos.rm = ?1")
	Optional<Aluno> findByRm(String rm);

	List<Transaction> findAllByDataBetween(LocalDateTime beginDate, LocalDateTime endDate);

}
