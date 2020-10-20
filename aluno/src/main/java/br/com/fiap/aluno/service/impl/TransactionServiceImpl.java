package br.com.fiap.aluno.service.impl;

import br.com.fiap.aluno.dto.TransactionDTO;
import br.com.fiap.aluno.dto.TransactionRequest;
import br.com.fiap.aluno.entity.Aluno;
import br.com.fiap.aluno.entity.Transaction;
import br.com.fiap.aluno.repository.AlunoRepository;
import br.com.fiap.aluno.repository.TransactionRepository;
import br.com.fiap.aluno.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private AlunoRepository alunoRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  AlunoRepository alunoRepository) {
        this.transactionRepository = transactionRepository;
        this.alunoRepository = alunoRepository;
    }

    @Override
    public TransactionDTO validateTransaction(TransactionRequest transactionRequest) {
        log.info("Verificando transação %s: %d" + transactionRequest.getRm(), transactionRequest.getValue().doubleValue());
        Aluno aluno = alunoRepository.findByRm(transactionRequest.getRm())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        log.info("Aluno encontrado %s", aluno.getNome());

        validateAluno(aluno, transactionRequest);

        Transaction auth = new Transaction();
        auth.setAluno(aluno);
        auth.setData(LocalDateTime.now());
        auth.setValor(transactionRequest.getValue());

        auth = transactionRepository.save(auth);

        return auth.toModel();
    }

    private void validateAluno(Aluno aluno, TransactionRequest transactionRequest) {
        if (!aluno.getRm().equals(transactionRequest.getRm())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno inválido");
        }
        if (!aluno.getCpf().equals(transactionRequest.getCpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CVV inválido");
        }
        if (transactionRequest.getValue().compareTo(Double.valueOf(0)) != 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor sem transação");
        }
    }

}
