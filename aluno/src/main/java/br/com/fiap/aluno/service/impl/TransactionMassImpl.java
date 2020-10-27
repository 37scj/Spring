package br.com.fiap.aluno.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import br.com.fiap.aluno.dto.AlunoDTO;
import br.com.fiap.aluno.dto.TransactionRequest;
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

	/**
	 * 
	 */
	@Override
	public Optional<AlunoDTO> findById(Long id , Integer transactionaAmount) {
		if (id == null) {
			return Optional.empty();
		}else if(alunoRepository.findById(id).isPresent()) {
			log.info("ALUNO RECUPERADO , ACIONANDO A INSERCAO DE MASSA DE DADOS : " + transactionaAmount);

			try {
				if(transactionaAmount != 0 && transactionaAmount != null) {
					int i = 0;
					i = inserirMassa(id, transactionaAmount, i);
				}
			}catch(Exception e) {
				return Optional.empty();
			}
		}else {
			return Optional.empty();
		}
		return null;
		
	}

	/**
	 * Metodo responsavel por realizar a insercao de massa de dados
	 * @param id
	 * @param transactionaAmount
	 * @param i
	 * @return
	 */
	private int inserirMassa(Long id, Integer transactionaAmount, int i) {
		while(i < transactionaAmount) {
			Aluno aluno = alunoRepository.findById(id).get();
			serviceImpl.validateTransaction(new TransactionRequest(aluno.getRm(),aluno.getCpf(), new Random().nextDouble()));
			i++;
		}
		return i;
	}


}
