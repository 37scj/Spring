package br.com.fiap.aluno.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.aluno.entity.Aluno;
import br.com.fiap.aluno.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query(value = "SELECT DISTINCT alunos.cpf , alunos.nome , alunos.rm from Aluno alunos where alunos.rm = :rm")
	Optional<Aluno> findyByRmRepository(@Param("rm") String rm);

	List<Transaction> findAllByDataBetween(LocalDateTime beginDate, LocalDateTime endDate);

}
