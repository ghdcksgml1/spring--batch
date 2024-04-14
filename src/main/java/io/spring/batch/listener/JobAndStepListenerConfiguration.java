package io.spring.batch.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobAndStepListenerConfiguration {

    private static final String JOB_NAME = "listenerJob";

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public Job job() {
        return new JobBuilder(JOB_NAME, jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .next(step2())
                .listener(new CustomJobExecutionListener())
                .listener(new CustomAnnotationJobExecutionListener())
                .build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .tasklet((contribution, chunkContext) -> RepeatStatus.FINISHED, platformTransactionManager)
                .listener(new CustomStepExecutionListener())
                .build();
    }

    @Bean
    public Step step2() {
        return new StepBuilder("step2", jobRepository)
                .tasklet((contribution, chunkContext) -> RepeatStatus.FINISHED, platformTransactionManager)
                .listener(new CustomStepExecutionListener())
                .build();
    }
}
