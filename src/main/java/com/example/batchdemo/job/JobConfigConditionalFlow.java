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
public class JobConfigConditionalFlow {

    @Bean
    public Job job(JobRepository jobRepository,
                   Step step1,
                   Step step2,
                   Step step3) {
        */
/* step1이 성공하면 step2로 이동, step1이 실패하면 step3로 이동 *//*

        //'*' : 0개 이상문자열과 일치 -> 'c*t' cat and count
        //'?': 정확하게 문자열과 일치 -> 'c?t' cat but not count
        //to(Step) : on 조건에 만족하면 해당 Step 으로 이동
        //from(Step) : 이전에 등록한 단계로 돌아가서 새 경로를 시작
        //종료의 기준은: completed, failed, stopped 가 있다.
        return new JobBuilder("job", jobRepository)
                .start(step1)
                    .on("*").to(step2)
                .from(step1).on("FAILED").to(step3)
                //.from(step1).on("FAILED").fail() //실패 하면 종료 하도록 한다.
                .end()
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
    public Step step3(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
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
}
*/
