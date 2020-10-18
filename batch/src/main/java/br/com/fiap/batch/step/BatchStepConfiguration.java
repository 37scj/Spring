package br.com.fiap.batch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.batch.model.AlunoDTO;

@Configuration
public class BatchStepConfiguration {
	
	@Autowired
	private StepBuilderFactory stepbuilderFactory;

	/**
	 * Metodo reponsavel por realizar os steps do Job
	 * @param leitor
	 * @param escritor
	 * @return
	 */
	@Bean
	public Step leituraArquivoLarguraFixa(ItemReader<AlunoDTO> leitor ,JdbcBatchItemWriter<AlunoDTO> databaseWriter){
		return stepbuilderFactory
				.get("leituraArquivoLarguraFixa")
				.<AlunoDTO,AlunoDTO>chunk(1) //A cada item eu crio uma nova transacao 
				.reader(leitor) //ler Arquivo
//				.writer(escritor) //apenas exibe os dados do arquivo 
				.writer(databaseWriter) /// Persistir na base
				.build();
						
	}
	
	
	
	

}
