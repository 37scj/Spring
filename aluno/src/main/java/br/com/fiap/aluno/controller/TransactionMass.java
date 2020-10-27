package br.com.fiap.aluno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.aluno.dto.AlunoDTO;
import br.com.fiap.aluno.service.TransactionMassService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("transacao")
public class TransactionMass {


	@Autowired
	private TransactionMassService transactionMassService;

	/**
	 * Metodo responsavel por buscar
	 * aluno por id e processar um loop para
	 * gerar massa de transacao de dados
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "Busca informações de um aluno")
	@GetMapping("/{id}")
	public AlunoDTO getById(@PathVariable Long id, @RequestParam(name = "amount" , required = false) Integer transactionaAmount) {
		return transactionMassService.findById(id,transactionaAmount).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Aluno não encontrado (id: " + id.toString() + ")"));
	}


}
