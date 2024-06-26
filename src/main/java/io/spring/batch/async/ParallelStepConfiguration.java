//package io.spring.batch.async;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.core.step.tasklet.TaskletStep;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.TaskExecutor;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class ParallelStepConfiguration {
//
//    private static final String JOB_NAME = "asyncJob";
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public Job job() {
//        return new JobBuilder(JOB_NAME, jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(flow1())
//                .split(taskExecutor()).add(flow2())
//                .end()
//                .listener(new StopWatchJobListener())
//                .build();
//    }
//
//    @Bean
//    public Flow flow2() {
//        TaskletStep step2 = new StepBuilder("step2", jobRepository)
//                .tasklet(tasklet(), platformTransactionManager).build();
//
//        TaskletStep step3 = new StepBuilder("step3", jobRepository)
//                .tasklet(tasklet(), platformTransactionManager).build();
//
//        return new FlowBuilder<Flow>("flow2")
//                .start(step2)
//                .next(step3)
//                .build();
//    }
//
//    @Bean
//    public Flow flow1() {
//
//        TaskletStep step1 = new StepBuilder("step1", jobRepository)
//                .tasklet(tasklet(), platformTransactionManager).build();
//
//        return new FlowBuilder<Flow>("flow1")
//                .start(step1)
//                .build();
//    }
//
//    @Bean
//    public Tasklet tasklet() {
//
//        return new CustomTasklet();
//    }
//
//
//    @Bean
//    public TaskExecutor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(4);
//        executor.setMaxPoolSize(8);
//        executor.setThreadNamePrefix("async-thread-");
//
//        return executor;
//    }
//}
