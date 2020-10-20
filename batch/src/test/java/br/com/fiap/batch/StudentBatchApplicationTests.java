package br.com.fiap.batch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {StudentBatchApplication.class, BatchConfig.class})
class StudentBatchApplicationTests {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private Job job;
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    void batchProcessFileToDatabase() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, SQLException {

        JobExecution run = jobLauncherTestUtils.getJobLauncher()
                .run(job, jobLauncherTestUtils.getUniqueJobParameters());
        Assertions.assertNotNull(run);
        assertEquals(BatchStatus.COMPLETED, run.getStatus());

        ResultSet resultSet = dataSource.getConnection()
                .prepareStatement("select count(*) from tb_aluno")
                .executeQuery();
        await().atMost(10, TimeUnit.SECONDS)
                .until(() -> {
                    resultSet.last();
                    return resultSet.getInt(1) >= 3;
                });
        assertTrue(resultSet.getInt(1) >= 3);
        System.out.println(resultSet.getInt(1));

        ResultSet result = dataSource.getConnection()
                .prepareStatement("select nome from tb_aluno")
                .executeQuery();
        while(result.next()){
            System.out.println( result.getString(1) );
        }
    }

}
