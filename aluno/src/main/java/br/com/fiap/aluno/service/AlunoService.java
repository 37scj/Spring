package br.com.fiap.aluno.service;

import java.util.List;
import java.util.Optional;

import br.com.fiap.aluno.dto.AlunoDTO;

public interface AlunoService {
	
	AlunoDTO create(AlunoDTO alunoPresenter);
    List<AlunoDTO> findAll();
    Optional<AlunoDTO> findById(Long id);
	void deleteById(Long id);

}
