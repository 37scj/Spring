package br.com.fiap.aluno.repository;

import br.com.fiap.aluno.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByDataBetween(LocalDateTime beginDate, LocalDateTime endDate);

}
