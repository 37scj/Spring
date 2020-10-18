package br.com.fiap.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * JobLauncher 
 * 			-> Executa , 
 * 			-> reinicializa e faz a validacao de parametos
 * 
 * Job : executa os steps
 * 		-> Step : mantem os dados salvos no jobRepositorys (metadados) para controlar os fluxos do job
 * 			-> JobRepository : metadados do spring para controlar fluxo de execucao
 * 				-> metadados
 * 					-> jobInstance : Logica do job do inicio ao fim
 * 					-> jobExecution : 1 job associado a multiplas execucoes logicas (jobInstancias)
 * 							que estao associadas a multiplas execucoes fisicas (jobExecution)
 * 
 * 
 * Tasklet : Tarefas de pre processamento
 * 		- Limpeza de arquivo
 * 		- Criacao de diretorio
 * 
 * Chuncks : Tarefas complexas que precisam ser realizadas em pedacos
 * 		- Leitura 
 * 		- Processamento
 * 		- Escrita
 * 
 * 		Cada chunck possui sua propria transacao ou seja
 * 		caso ocorra um erro em alguma fase da chunck tudo o processo que foi feito eh salvo
 * 
 * 
 * @author lucasrodriguesdonascimento
 *
 */

@EnableBatchProcessing
@Configuration
public class BatchJobConfiguration {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	/**
	 * Metodo responsavel por inicialiazar a tarefa
	 * @param leituraArquivoLarguraFixa
	 * @return
	 */
	@Bean
	public Job readFile(Step leituraArquivoLarguraFixa) {
		return jobBuilderFactory
				.get("lerArquivo")
				.start(leituraArquivoLarguraFixa)
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
	
}
