package br.com.fiap.batch.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.batch.model.AlunoDTO;
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
 * @author lucasrodriguesdonascimento
 *
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Value("${file.path}")
	private String fileName;

	Logger logger = LoggerFactory.getLogger(BatchConfig.class);
	
	//Builders
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepbuilderFactory;
	
	/**
	 * incrementer : Executa o job e adiciona um ID a cada execucao para
	 * ser permitido a execucao mais de uma vez
	 * 
	 * @return
	 */
	@Bean
	public Job readFile() {
		return jobBuilderFactory
				.get("lerArquivo")
				.start(readFiles())
				.incrementer(new RunIdIncrementer())
				.build();
	}

	private Step readFiles() {
		return stepbuilderFactory
				.get("readFiles")
				.tasklet(executeFiles()).build();
		
	}

	/**
	 * Tasklet : Tarefas de pre processamento
	 * @return
	 */
	private Tasklet executeFiles() {
		return new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				logger.info("INICIANDO A LEITURA DO ARQUIVO : " + fileName);

				readFileTxt();
				
				return RepeatStatus.FINISHED;
			}
			
			/**
			 * Metodo responsavel por realizar a leitura
			 */
			private void readFileTxt() {
				AlunoDTO alunoDTO = null;
				List<AlunoDTO> listAluno = new ArrayList<>();
				
				try {
					FileReader arq = new FileReader(fileName);
					BufferedReader lerArq = new BufferedReader(arq);
					
					String linha = lerArq.readLine();
					
					while (linha != null) {	
						if(linha.length() > 0 && !linha.contains("--------------------------")) {


							String nome = String.valueOf(linha.substring(0,40));
							String rm = String.valueOf(linha.substring(41,49).trim());
							String cpf = String.valueOf(linha.substring(49,55));

							logger.info(nome,rm,cpf);
							
							alunoDTO = new AlunoDTO();
							alunoDTO.setNome(nome);
							alunoDTO.setRm(Integer.parseInt(rm));
							alunoDTO.setCpf(cpf);

							listAluno.add(alunoDTO);
						}
						linha = lerArq.readLine();
					}
					
					arq.close();
					
				} catch(IOException e) {
					logger.error("Erro na abertura do arquivo: %s.\n", e.getMessage());
				}catch(StringIndexOutOfBoundsException e) {
					logger.error("Falha ao capturar a posicao da String ", e.getMessage());
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
	}
	
}
