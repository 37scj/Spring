package br.com.fiap.aluno.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.aluno.dto.TransactionDTO;
import br.com.fiap.aluno.dto.TransactionRequest;
import br.com.fiap.aluno.entity.Aluno;
import br.com.fiap.aluno.entity.Transaction;
import br.com.fiap.aluno.repository.AlunoRepository;
import br.com.fiap.aluno.repository.TransactionRepository;
import br.com.fiap.aluno.service.TransactionService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Gabriel Ribeiro
 */
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
		log.info("Verificando transação " + transactionRequest.getRm() + ": " + transactionRequest.getValue().doubleValue());
		Aluno aluno = transactionRepository.findyByRmRepository(transactionRequest.getRm())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		log.info("Aluno encontrado " + aluno.getNome());

		validateAluno(aluno, transactionRequest);

		Transaction auth = new Transaction();
		auth.setAluno(aluno);
		auth.setData(LocalDateTime.now());
		auth.setValor(transactionRequest.getValue());

		auth = transactionRepository.save(auth);

		return new TransactionDTO(auth);
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

	@Override
	public List<TransactionDTO> findAll(){
		return transactionRepository.findAll()
				.stream()
				.map(TransactionDTO::new)
				.collect(Collectors.toList());
	}

    @Override
    public List<TransactionDTO> findByDateBetween(String dateTimeBegin, String dateTimeEnd) {
        LocalDateTime[] dateTimeList = validateAndConvertDate(dateTimeBegin, dateTimeEnd);


        return transactionRepository.findAllByDataBetween(dateTimeList[0], dateTimeList[1])
                .stream()
                .map(TransactionDTO::new)
                .collect(Collectors.toList());
    }

    private LocalDateTime[] validateAndConvertDate(String dateTimeBegin, String dateTimeEnd){
        if(dateTimeBegin.isEmpty() || dateTimeBegin == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de inicio inválida");
        }

        if(dateTimeEnd.isEmpty() || dateTimeEnd == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de fim inválida");
        }

        LocalDateTime localDateTimeBegin = LocalDateTime.parse(dateTimeBegin);
        LocalDateTime localDateTimeEnd = LocalDateTime.parse(dateTimeEnd);

        LocalDateTime[] localDateTimes = new LocalDateTime[2];

        localDateTimes[0] = localDateTimeBegin;
        localDateTimes[1] = localDateTimeEnd;

        return localDateTimes;
    }


}
