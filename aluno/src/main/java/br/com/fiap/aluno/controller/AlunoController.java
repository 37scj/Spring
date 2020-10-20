package br.com.fiap.aluno.controller;

import br.com.fiap.aluno.dto.AlunoDTO;
import br.com.fiap.aluno.service.AlunoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Classe controlladora
 *
 * @author lucasrodriguesdonascimento
 */
@RestController
@RequestMapping("alunos")
public class AlunoController {


    @Autowired
    private AlunoService alunoService;


    /**
     * Criar aluno
     *
     * @param aluno
     * @return
     */
    @ApiOperation(value = "Cria um novo drone")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AlunoDTO addDrone(@RequestBody AlunoDTO aluno) {
        return alunoService.create(aluno);
    }

    /**
     * Buscar aluno por ID
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Busca informações de um aluno")
    @GetMapping("/{id}")
    public AlunoDTO getById(@PathVariable Long id) {
        return alunoService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Aluno não encontrado (id: " + id.toString() + ")"));
    }

    /**
     * Listar aluno
     *
     * @return
     */
    @ApiOperation(value = "Lista todos alunos")
    @GetMapping()
    public List<AlunoDTO> getAll() {
        return alunoService.findAll();
    }


    /**
     * Remover aluno
     *
     * @param id
     */
    @ApiOperation(value = "Exclui um aluno")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void deleteDrone(@PathVariable Long id) {
        alunoService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Aluno não encontrado (id: " + id.toString() + ")"));
        alunoService.deleteById(id);
    }


}
