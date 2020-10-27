package br.com.fiap.aluno.service;

import java.util.Optional;

import br.com.fiap.aluno.dto.AlunoDTO;

public interface TransactionMassService {
	
	Optional<AlunoDTO> findById(Long id,Integer transactionaAmount);
 
}
