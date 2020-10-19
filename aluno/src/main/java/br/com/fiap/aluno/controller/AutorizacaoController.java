package br.com.fiap.aluno.controller;

import br.com.fiap.aluno.dto.AuthorizationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/auth")
@RestController
public class AutorizacaoController {

    @ApiOperation("Envia uma request para autorização de transação para um cartão de aluno")
    @PostMapping()
	public ResponseEntity getAuthorization(@RequestBody AuthorizationRequest authorizationRequest){
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

}
