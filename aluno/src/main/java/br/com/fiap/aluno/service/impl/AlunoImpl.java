package br.com.fiap.aluno.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.aluno.dto.AlunoDTO;
import br.com.fiap.aluno.entity.Aluno;
import br.com.fiap.aluno.repository.AlunoRepository;
import br.com.fiap.aluno.service.AlunoService;

@Service
public class AlunoImpl implements AlunoService{
	
    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AlunoImpl.class);

	
	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public AlunoDTO create(AlunoDTO alunoDTO) {
		
		Aluno aluno = new Aluno(alunoDTO);
		Aluno savedAluno = alunoRepository.save(aluno);
		return new AlunoDTO(savedAluno);

	}

	@Override
	public List<AlunoDTO> findAllDrone() {
		return alunoRepository.findAll().stream().map(AlunoDTO::new).collect(Collectors.toList());
	}

	@Override
	public Optional<AlunoDTO> findById(Long id) {
		if (id == null) return Optional.empty();
        return Optional.ofNullable(new AlunoDTO(alunoRepository.findById(id).orElse(null)));
	}

	@Override
	public void deleteById(Long id) {
		alunoRepository.deleteById(id);
		
	}
	
	

}
