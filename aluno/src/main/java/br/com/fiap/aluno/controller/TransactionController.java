package br.com.fiap.aluno.controller;

import br.com.fiap.aluno.dto.TransactionDTO;
import br.com.fiap.aluno.dto.TransactionRequest;
import br.com.fiap.aluno.service.TransactionService;
import br.com.fiap.aluno.util.WriteCSVToResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Gabriel Ribeiro
 */
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

    @ApiOperation("Lista todas as transações feitas entre um período")
    @GetMapping(name = "/listAllBetween", produces = "text/csv")
    public void findAllBetween(@RequestParam String dateTimeBegin, @RequestParam String dateTimeEnd, HttpServletResponse httpServletResponse){
        List<TransactionDTO> transactionDTOList = transactionService.findByDateBetween(dateTimeBegin, dateTimeEnd);

        try {
            WriteCSVToResponse.writeTransactions(httpServletResponse.getWriter(), transactionDTOList);
        }catch(IOException io){
            httpServletResponse.setStatus(400);
        }

    }

}
