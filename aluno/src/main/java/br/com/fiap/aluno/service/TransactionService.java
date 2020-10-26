package br.com.fiap.aluno.service;

import br.com.fiap.aluno.dto.TransactionDTO;
import br.com.fiap.aluno.dto.TransactionRequest;

import java.util.List;

public interface TransactionService {

    TransactionDTO validateTransaction(TransactionRequest transactionRequest);

    List<TransactionDTO> findAll();

    List<TransactionDTO> findByDateBetween(String dateTimeBegin, String dateTimeEnd);
}
