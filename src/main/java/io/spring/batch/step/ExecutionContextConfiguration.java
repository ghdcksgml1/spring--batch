//package io.spring.batch.step;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class ExecutionContextConfiguration {
//    private static final String JOB_PARAMETER = "Job";
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//    private final ExecutionContextTasklet1 executionContextTasklet1;
//    private final ExecutionContextTasklet2 executionContextTasklet2;
//    private final ExecutionContextTasklet3 executionContextTasklet3;
//    private final ExecutionContextTasklet4 executionContextTasklet4;
//
//    @Bean
//    public Job JobExecution() {
//        return new JobBuilder(JOB_PARAMETER, jobRepository)
//                .start(step1())
//                .next(step2())
//                .next(step3())
//                .next(step4())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return new StepBuilder(JOB_PARAMETER + "Step1", jobRepository)
//                .tasklet(executionContextTasklet1, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return new StepBuilder(JOB_PARAMETER + "Step2", jobRepository)
//                .tasklet(executionContextTasklet2, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step step3() {
//        return new StepBuilder(JOB_PARAMETER + "Step3", jobRepository)
//                .tasklet(executionContextTasklet3, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step step4() {
//        return new StepBuilder(JOB_PARAMETER + "Step4", jobRepository)
//                .tasklet(executionContextTasklet4, platformTransactionManager)
//                .build();
//    }
//}
