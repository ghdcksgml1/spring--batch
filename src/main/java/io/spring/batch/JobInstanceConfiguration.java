package io.spring.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class JobInstanceConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public Job jobInstance() {
        return new JobBuilder("jobInstance", jobRepository)
                .start(jobInstanceStep1())
                .next(jobInstanceStep2())
                .build();
    }

    @Bean
    public Step jobInstanceStep1() {
        return new StepBuilder("jobInstanceStep1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("jobInstanceStep1");
                    return RepeatStatus.FINISHED;
                }, platformTransactionManager)
                .build();
    }

    @Bean
    public Step jobInstanceStep2() {
        return new StepBuilder("jobInstanceStep2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("jobInstanceStep2");
                    return RepeatStatus.FINISHED;
                }, platformTransactionManager)
                .build();
    }
}
