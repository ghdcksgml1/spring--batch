package io.spring.batch.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

//@Configuration
//@RequiredArgsConstructor
//public class JobExecutionConfiguration {
//    private static final String JOB_PARAMETER = "JobExecution";
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public Job JobExecution() {
//        return new JobBuilder(JOB_PARAMETER, jobRepository)
//                .start(JobExecutionStep1())
//                .next(JobExecutionStep2())
//                .build();
//    }
//
//    @Bean
//    public Step JobExecutionStep1() {
//        return new StepBuilder(JOB_PARAMETER + "Step1", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(JOB_PARAMETER);
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step JobExecutionStep2() {
//        return new StepBuilder(JOB_PARAMETER + "Step2", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(JOB_PARAMETER);
////                    throw new RuntimeException("step2 has failed");
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//}
