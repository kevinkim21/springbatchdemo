package com.example.batchdemo.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class JobConfiguration {

    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    @StepScope
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        final Tasklet tasklet = new Tasklet() {
            private int count = 0;
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                count++;
                if(count == 15) {
                    log.info("Tasklet Finished {}", count);
                    return RepeatStatus.FINISHED;
                }
                log.info("Tasklet Continuable {}", count);
                return RepeatStatus.CONTINUABLE;
            }
        };
        return new StepBuilder("step", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }
}
