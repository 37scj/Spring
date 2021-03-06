package br.com.fiap.aluno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.aluno.service.TransactionMassService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("transacao")
public class TransactionMassController {


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
	@ApiOperation(value = "Geracao de massa de dados por id de usuario")
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@ApiParam(value="Adicionar o id do usuario") @PathVariable Long id, @ApiParam(value="Adicionar a quantidade de vezes que deseja gerar transacao") @RequestParam(name = "amount" , required = true) Integer transactionaAmount){
		String status = transactionMassService.findById(id, transactionaAmount);
		
		if(status.equals("OK")) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(status);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha");
		}

	}


}
