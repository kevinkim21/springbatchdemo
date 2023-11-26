/*
package com.example.batchdemo.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
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
public class JobConfig_01 {

    @Bean
    public Job job(JobRepository jobRepository,
                   Step step1,
                   Step step2) {
        return new JobBuilder("job", jobRepository)
                .start(step1)
                .next(step2)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        final Tasklet tasklet = new Tasklet() {
            private int count = 0;

            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
                count++;
                if (count == 10) {
                    log.info("Finished {}", count);
                    return RepeatStatus.FINISHED;
                } else {
                    log.info("Hello, world!");
                    return RepeatStatus.CONTINUABLE;
                }
            }
        };
        return new StepBuilder("step1", jobRepository)
                .tasklet(tasklet, transactionManager)
                //.allowStartIfComplete(true) //해당 스텝이 성공해도 재시작 허용
                //.startLimit(3) //해당 스텝 시작 제한수
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step2", jobRepository)
                .chunk(10, transactionManager)
                .reader(() -> "read")
                .processor(read -> "process")
                .writer(process -> {})

                .faultTolerant()
                //.skipLimit(10) //스킵을 몇번까지 허용할지 정의
                //.skip(ClassCastException.class) //skip할 exception
                //.noSkip(IllegalStateException.class) //스킵은 하지만 예외적으로 스킵을 하지 말아야할 클래스 지정
                //.retryLimit(3) //실패 할 경우 리트라이 횟수 지정
                //.retry(ClassCastException.class) //재시도 할 exception 클래스 지정
                .build();

    }
}
*/
