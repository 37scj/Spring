package br.com.fiap.batch.writer;

import org.springframework.batch.item.ItemWriter;
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

}
