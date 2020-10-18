package br.com.fiap.batch.reader;


import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import br.com.fiap.batch.model.AlunoDTO;

@Configuration
public class BatchConfigLeitorArquivos {
	
	/**
	 * Metodo responsavel por ler o arquivo
	 * @param resource
	 * @return
	 */
	@Bean
	public FlatFileItemReader<AlunoDTO> lerArquivo(@Value("${file.chunk}") Resource resource){
		return new FlatFileItemReaderBuilder<AlunoDTO>()
				
				.name("leituraArquivo")
				.resource(resource)
				.fixedLength()
				.columns(new Range[] {new Range(1, 41) , new Range(42, 50) , new Range(50,55)})
				.names(new String [] {"nome" , "rm" , "cpf"})
				.targetType(AlunoDTO.class)
				.build();
		
	}

}
