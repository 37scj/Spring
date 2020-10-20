package br.com.fiap.aluno.service;

import br.com.fiap.aluno.dto.TransactionDTO;
import br.com.fiap.aluno.dto.TransactionRequest;

public interface TransactionService {

    TransactionDTO validateTransaction(TransactionRequest transactionRequest);

}
