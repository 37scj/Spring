package br.com.fiap.batch.job;

import br.com.fiap.batch.model.Aluno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

/**
 * JobLauncher
 * -> Executa ,
 * -> reinicializa e faz a validacao de parametos
 * <p>
 * Job : executa os steps
 * -> Step : mantem os dados salvos no jobRepositorys (metadados) para controlar os fluxos do job
 * -> JobRepository : metadados do spring para controlar fluxo de execucao
 * -> metadados
 * -> jobInstance : Logica do job do inicio ao fim
 * -> jobExecution : 1 job associado a multiplas execucoes logicas (jobInstancias)
 * que estao associadas a multiplas execucoes fisicas (jobExecution)
 * <p>
 * <p>
 * Tasklet : Tarefas de pre processamento
 * - Limpeza de arquivo
 * - Criacao de diretorio
 * <p>
 * Chuncks : Tarefas complexas que precisam ser realizadas em pedacos
 * - Leitura
 * - Processamento
 * - Escrita
 * <p>
 * Cada chunck possui sua propria transacao ou seja
 * caso ocorra um erro em alguma fase da chunck tudo o processo que foi feito eh salvo
 *
 * @author lucasrodriguesdonascimento
 */

@EnableBatchProcessing
@Configuration
public class BatchJobConfiguration {

    /**
     * Metodo responsavel por ler o arquivo
     */
    @Bean
    public FlatFileItemReader<Aluno> lerArquivo(@Value("${file.chunk}") Resource resource) {
        return new FlatFileItemReaderBuilder<Aluno>()
                .name("leituraArquivo")
                .resource(resource)
                .comments("#", "--", "//")
                .fixedLength()
                .columns(new Range(1, 41), new Range(42, 49), new Range(50, 55))
                .names("nome", "rm", "cpf")
                .targetType(Aluno.class)
                .build();
    }

    @Bean
    public ItemProcessor<Aluno, Aluno> processor() {
        return (aluno) -> {
            aluno.setNome(aluno.getNome().trim());
            aluno.setRm(aluno.getRm().trim());
            aluno.setCpf(aluno.getCpf().trim().replaceAll("\\-", ""));
            return aluno;
        };
    }

//    @Bean
//    public ItemWriter<Aluno> leituraArquivoLarguraFixaWriter() {
//        Logger log = LoggerFactory.getLogger("Batch");
//        return indice -> indice.forEach(a -> log.info(a.toString()));
//    }
    @Bean
    public JdbcBatchItemWriter<Aluno> databaseWriter(DataSource datasource) {
        return new JdbcBatchItemWriterBuilder<Aluno>()
                .dataSource(datasource)
                .sql("insert into tb_aluno (nome, rm, cpf) values (:nome, :rm, :cpf)")
                .beanMapped()
                .build();
    }

    /**
     * Metodo reponsavel por realizar os steps do Job
     */
    @Bean
    public Step leituraArquivoLarguraFixa(StepBuilderFactory stepbuilderFactory,
                                          ItemReader<Aluno> leitor,
                                          ItemWriter<Aluno> writer,
                                          ItemProcessor<Aluno, Aluno> processor) {
        return stepbuilderFactory
                .get("leituraArquivoLarguraFixa")
                .<Aluno, Aluno>chunk(50) //A cada item eu crio uma nova transacao
                .reader(leitor) //ler Arquivo
                .processor(processor)
                .writer(writer) /// Persistir na base
                .build();
    }

    /**
     * Metodo responsavel por inicialiazar a tarefa
     */
    @Bean
    public Job readFile(JobBuilderFactory jobBuilderFactory,
                        Step lerArquivoLarguraFixa) {
        return jobBuilderFactory
                .get("lerArquivo")
                .start(lerArquivoLarguraFixa)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
