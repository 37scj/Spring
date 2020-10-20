package br.com.fiap.aluno;

import br.com.fiap.aluno.dto.AlunoDTO;
import br.com.fiap.aluno.dto.TransactionDTO;
import br.com.fiap.aluno.dto.TransactionRequest;
import br.com.fiap.aluno.service.AlunoService;
import br.com.fiap.aluno.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AlunoApplication.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.datasource.url=jdbc:h2:mem:batchDB;DB_CLOSE_ON_EXIT=TRUE"})
public class TransacoesTests {

    @Autowired
    AlunoService alunoService;
    @Autowired
    TransactionService transactionService;

    private static final String NOME_TESTE = "Teste Teste TesteTeste";

    private static final String[] NOMES_TESTE = new String[]{"Teste", "Teste", "TesteTeste"};

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    /**
     * Classe para receber a lista de alunos do endpoint
     */
    public static class AlunoDTOList extends ArrayList<AlunoDTO> implements Serializable {
        public AlunoDTOList() {
        }
    }

    @Test
    public void test_get_alunos_list() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/alunos";
        URI uri = new URI(baseUrl);

        ResponseEntity<AlunoDTOList> result = this.restTemplate.getForEntity(uri, AlunoDTOList.class);

        Assertions.assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void test_add_aluno() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort + "/alunos";
        URI uri = new URI(baseUrl);
        AlunoDTO aluno = new AlunoDTO(null, "Gabriel", "11111", "22222");
        HttpEntity<AlunoDTO> request = new HttpEntity<>(aluno, null);

        ResponseEntity<AlunoDTO> result = this.restTemplate.postForEntity(uri, request, AlunoDTO.class);

        Assertions.assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    @Order(0)
    void should_add_alunos_count() {
        Arrays.stream(NOMES_TESTE)
                .map((nome) -> new AlunoDTO(null, nome, "123", "123"))
                .forEach((aluno) -> alunoService.create(aluno));
        Assertions.assertEquals(NOMES_TESTE.length, alunoService.findAll().stream().count());
    }

    @Test
    @Order(1)
    void should_add_aluno_with_id() {
        AlunoDTO aluno = new AlunoDTO(10L, "teste20", "123", "123");
        aluno = alunoService.create(aluno);
        Assertions.assertNotNull(aluno);
        Assertions.assertNotNull(aluno.getId());
    }

    @Test
    @Order(2)
    void should_get_aluno_by_id() {
        AlunoDTO aluno = alunoService.findById(1L).orElse(null);
        Assertions.assertNotNull(aluno);
    }

    @Test
    @Order(3)
    void should_add_aluno() {
        AlunoDTO aluno = new AlunoDTO(null, NOME_TESTE, "12345", "98765");
        alunoService.create(aluno);
        Optional<AlunoDTO> byRm = alunoService.findByRm("98765");
        Assertions.assertEquals(true, byRm.isPresent());
        Assertions.assertNotNull(byRm.get().getId());
        Assertions.assertEquals("98765", byRm.get().getRm());
    }

    @Test
    @Order(4)
    void should_invalidate_transaction1() {
        TransactionRequest transactionRequest = new TransactionRequest("12345", "12345", 0d);
        Assertions.assertThrows(ResponseStatusException.class, () -> transactionService.validateTransaction(transactionRequest));
    }

    @Test
    @Order(4)
    void should_invalidate_transaction2() {
        TransactionRequest transactionRequest = new TransactionRequest("98765", "", 0d);
        Assertions.assertThrows(ResponseStatusException.class, () -> transactionService.validateTransaction(transactionRequest));
    }

    @Test
    @Order(4)
    void should_invalidate_transaction3() {
        TransactionRequest transactionRequest = new TransactionRequest("98765", "12345", 0d);
        Assertions.assertThrows(ResponseStatusException.class, () -> transactionService.validateTransaction(transactionRequest));
    }

    @Test
    @Order(5)
    void should_validate_transaction() {
        TransactionRequest transactionRequest = new TransactionRequest("98765", "12345", 200.0d);
        TransactionDTO transactionDTO = transactionService.validateTransaction(transactionRequest);
        Assertions.assertEquals(NOME_TESTE, transactionDTO.getAluno().getNome());
        Assertions.assertEquals(200d, transactionDTO.getValor());
    }

    @Test
    @Order(10)
    void should_print_all_alunos() {
        Logger log = LoggerFactory.getLogger("alunos");
        alunoService.findAll().stream().map(AlunoDTO::toString).forEach(log::info);
    }

    @Test
    @Order(10)
    void should_print_all_transacoes() {
        Logger log = LoggerFactory.getLogger("transacoes");
        transactionService.findAll().stream().map(TransactionDTO::toString).forEach(log::info);
    }

}
