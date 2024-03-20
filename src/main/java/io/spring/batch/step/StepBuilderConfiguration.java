//package io.spring.batch.step;
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
//public class StepBuilderConfiguration {
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public Job job() {
//        return new JobBuilder("Job", jobRepository)
//                .start(step1())
//                .next(step2())
//                .next(step3())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return new StepBuilder("step1", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step1 was executed");
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return new StepBuilder("step2", jobRepository)
//                .<String, String>chunk(3, platformTransactionManager)
//                .reader(() -> {
//                    return null;
//                })
//                .processor(item -> {
//                    return null;
//                })
//                .writer(items -> {
//
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step3() {
//        return new StepBuilder("step3", jobRepository)
//                .partitioner(step1())
//                .gridSize(2)
//                .build();
//    }
//
//    @Bean
//    public Step step4() {
//        return new StepBuilder("step4", jobRepository)
//                .job(inJob())
//                .build();
//    }
//
//    @Bean
//    public Step step5() {
//        return new StepBuilder("step5", jobRepository)
//                .flow(flow())
//                .build();
//    }
//
//    @Bean
//    public Job inJob() {
//        return new JobBuilder("inJob", jobRepository)
//                .start(step1())
//                .next(step2())
//                .next(step3())
//                .build();
//    }
//
//    @Bean
//    public Flow flow() {
//        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow");
//        return flowBuilder.start(step2())
//                .end();
//    }
//}
