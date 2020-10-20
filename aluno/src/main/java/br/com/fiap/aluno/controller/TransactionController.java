package br.com.fiap.aluno.controller;

import br.com.fiap.aluno.dto.TransactionDTO;
import br.com.fiap.aluno.dto.TransactionRequest;
import br.com.fiap.aluno.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/transaction")
@RestController
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation("Envia uma request para autorização de valor de transação para um cartão de aluno")
    @PostMapping()
    public ResponseEntity<TransactionDTO> newTransaction(@RequestBody TransactionRequest transactionRequest) {
        TransactionDTO auth = transactionService.validateTransaction(transactionRequest);
        return ResponseEntity.ok(auth);
    }

}
