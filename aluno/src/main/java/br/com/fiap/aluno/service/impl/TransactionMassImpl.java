package br.com.fiap.aluno.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.fiap.aluno.dto.TransactionRequestDTO;
import br.com.fiap.aluno.entity.Aluno;
import br.com.fiap.aluno.repository.AlunoRepository;
import br.com.fiap.aluno.service.TransactionMassService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionMassImpl implements TransactionMassService{

	private TransactionServiceImpl serviceImpl;
	private AlunoRepository alunoRepository;

	public TransactionMassImpl(TransactionServiceImpl serviceImpl,
			AlunoRepository alunoRepository) {
		this.serviceImpl = serviceImpl;
		this.alunoRepository = alunoRepository;
	}

	@Override
	public String findById(Long id , Integer transactionaAmount) {

		log.info("ALUNO RECUPERADO , ACIONANDO A INSERCAO DE MASSA DE DADOS : " + transactionaAmount);
		try {
			if(transactionaAmount != 0 && transactionaAmount != null) {
				inserirMassa(id, transactionaAmount);
				return "OK"; 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return "FAIL";
	}

	/**
	 * Metodo responsavel por realizar a insercao de massa de dados
	 * @param id
	 * @param transactionaAmount
	 * @param i
	 * @return
	 */
	private int inserirMassa(Long id, Integer transactionaAmount) {
		int i = 0;
		while(i < transactionaAmount) {
			Aluno aluno = alunoRepository.findById(id).get();
			serviceImpl.transactionGenerate(new TransactionRequestDTO(aluno.getId(),aluno.getRm(),aluno.getCpf(), new Random().nextDouble()));
			i++;
		}
		return i;
	}


}
