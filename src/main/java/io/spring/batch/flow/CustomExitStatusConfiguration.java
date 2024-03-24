//package io.spring.batch.flow;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.ExitStatus;
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
//public class CustomExitStatusConfiguration {
//
//    private static final String JOB_NAME = "CustomExitStatus";
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public Job job() {
//        return new JobBuilder(JOB_NAME, jobRepository)
//                .start(step1())
//                    .on("FAILED").to(step2())
//                    .on("PASS").stop()
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return new StepBuilder("step1", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(">> step1 has executed!");
//                    contribution.getStepExecution().setExitStatus(ExitStatus.FAILED);
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return new StepBuilder("step2", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(">> step2 has executed!");
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .listener(new PassCheckingListener())
//                .build();
//    }
//}
