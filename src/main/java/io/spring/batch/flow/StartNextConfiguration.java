//package io.spring.batch.flow;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class StartNextConfiguration {
//
//    private static final String JOB_NAME = "FlowJob";
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public Job flowJob() {
//        return new JobBuilder(JOB_NAME, jobRepository)
//                .start(flowA())
//                .next(step3())
//                .next(flowB())
//                .next(step6())
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Flow flowA() {
//        return new FlowBuilder<Flow>("flowA")
//                .start(step1())
//                .next(step2())
//                .end();
//    }
//
//    @Bean
//    public Step step1() {
//        return new StepBuilder("step1", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(">> step1 has executed!");
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
//                .build();
//    }
//
//    @Bean
//    public Step step3() {
//        return new StepBuilder("step3", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(">> step3 has executed!");
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Flow flowB() {
//        return new FlowBuilder<Flow>("flowB")
//                .start(step4())
//                .next(step5())
//                .end();
//    }
//
//    @Bean
//    public Step step4() {
//        return new StepBuilder("step4", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(">> step4 has executed!");
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step step5() {
//        return new StepBuilder("step5", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(">> step5 has executed!");
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step step6() {
//        return new StepBuilder("step6", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(">> step6 has executed!");
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//}
