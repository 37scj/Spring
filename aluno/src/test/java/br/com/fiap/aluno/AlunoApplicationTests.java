package br.com.fiap.aluno;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@PropertyMapping("spring.datasource.url=jdbc:h2:mem:batchDB;DB_CLOSE_ON_EXIT=TRUE")
@ContextConfiguration(classes = {AlunoApplication.class})
class AlunoApplicationTests {

    @Test
    void contextLoads() {
    }

}
