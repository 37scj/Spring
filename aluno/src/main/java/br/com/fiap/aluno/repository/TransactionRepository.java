package br.com.fiap.aluno.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.aluno.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findAllByDataBetween(LocalDateTime beginDate, LocalDateTime endDate);

}
