package io.spring.batch.step;

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

//@Configuration
//@RequiredArgsConstructor
//public class StepConfiguration {
//    private static final String JOB_PARAMETER = "Job";
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public Job JobExecution() {
//        return new JobBuilder(JOB_PARAMETER, jobRepository)
//                .start(step1())
//                .next(step2())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return new StepBuilder(JOB_PARAMETER + "Step1", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(JOB_PARAMETER);
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return new StepBuilder(JOB_PARAMETER + "Step2", jobRepository)
//                .tasklet(new CustomTasklet(), platformTransactionManager)
//                .build();
//    }
//}