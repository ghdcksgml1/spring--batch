package io.spring.batch.flow;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class JobScopeStepScopeConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public Job job() {
        return new JobBuilder("batchJob", jobRepository)
                .start(step1())
                .next(step2())
                .build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .tasklet(tasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    public Step step2() {
        return new StepBuilder("step2", jobRepository)
                .tasklet(tasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet tasklet() {
        System.out.println("=== 중단점 ===");
        return (contribution, chunkContext) -> {
            System.out.println("tasklet1 has executed!");
            return RepeatStatus.FINISHED;
        };
    }
}
