package br.com.fiap.aluno.repository;

import br.com.fiap.aluno.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
