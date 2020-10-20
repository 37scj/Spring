package br.com.fiap.aluno.service.impl;

import br.com.fiap.aluno.dto.TransactionRequest;
import br.com.fiap.aluno.repository.AlunoRepository;
import br.com.fiap.aluno.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

class TransactionServiceImplTest {

    @MockBean
    AlunoRepository alunoRepository;
    @MockBean
    TransactionRepository transactionRepository;

    TransactionRequest transactionRequest;

    @BeforeAll
    void setup() {
        transactionRequest = new TransactionRequest("12345", "12345", 0d);
    }

    @Test
    void validateTransaction(TransactionServiceImpl transactionService) {
        Mockito.when(alunoRepository.findByRm(transactionRequest.getRm())).thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(ResponseStatusException.class, ()->transactionService.validateTransaction(transactionRequest));
    }
}