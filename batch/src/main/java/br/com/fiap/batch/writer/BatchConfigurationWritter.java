package br.com.fiap.batch.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.batch.model.AlunoDTO;

/**
 * Essa classe apenas exibie os valores
 * do arquivo. metodo toString
 * @author lucasrodriguesdonascimento
 *
 */

@Configuration
public class BatchConfigurationWritter {
	
	@Bean
	public ItemWriter<AlunoDTO> leituraArquivoLarguraFixaWriter() {
		return indice -> indice.forEach(System.out::println);
	}
	
	@Bean
    public JdbcBatchItemWriter<AlunoDTO> databaseWriter(DataSource datasource) {
        return new JdbcBatchItemWriterBuilder<AlunoDTO>()
                .dataSource(datasource)
                .sql("insert into TB_ALUNO (NOME, RM, CPF) values (:nome, :rm, :cpf)")
                .beanMapped()
                .build();
    }

}
