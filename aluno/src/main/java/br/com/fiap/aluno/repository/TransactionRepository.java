package br.com.fiap.aluno.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.aluno.entity.Aluno;
import br.com.fiap.aluno.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query("SELECT DISTINCT rm from Aluno where rm = (:rm)")
	Optional<Aluno> findyByRmRepository(@Param("rm") String rm);

	List<Transaction> findAllByDataBetween(LocalDateTime beginDate, LocalDateTime endDate);

}
