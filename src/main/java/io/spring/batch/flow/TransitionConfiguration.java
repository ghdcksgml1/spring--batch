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
//public class TransitionConfiguration {
//
//    private static final String JOB_NAME = "transitionJob";
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public Job transitionJob() {
//        return new JobBuilder(JOB_NAME, jobRepository)
//                .start(step1())
//                    .on("FAILED").to(step2())
//                    .on("FAILED").stop()
//                .from(step1())
//                    .on("*").to(step3())
//                    .next(step4())
//                .from(step2())
//                    .on("*").to(step5())
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return new StepBuilder("step1", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("> step1 has executed!");
//                    contribution.setExitStatus(ExitStatus.FAILED);
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return new StepBuilder("step2", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("> step2 has executed!");
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step step3() {
//        return new StepBuilder("step3", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("> step3 has executed!");
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step step4() {
//        return new StepBuilder("step4", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("> step4 has executed!");
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step step5() {
//        return new StepBuilder("step5", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("> step5 has executed!");
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//}
