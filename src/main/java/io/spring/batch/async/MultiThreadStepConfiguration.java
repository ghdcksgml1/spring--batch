//package io.spring.batch.async;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.TaskExecutor;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Configuration
//@RequiredArgsConstructor
//public class MultiThreadStepConfiguration {
//
//    private static final String JOB_NAME = "asyncJob";
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    private final StopWatchJobListener stopWatchJobListener;
//
//    @Bean
//    public Job job() {
//        return new JobBuilder(JOB_NAME, jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(step1())
//                .listener(stopWatchJobListener)
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return new StepBuilder("step1", jobRepository)
//                .<String, String>chunk(100, platformTransactionManager)
//                .reader(itemReader())
//                .listener(new CustomItemReadListener())
//                .processor((ItemProcessor<? super String, String>) item -> item)
//                .listener(new CustomItemProcessListener())
//                .writer(chunk -> System.out.println(chunk.getItems()))
//                .listener(new CustomItemWriteListener())
//                .taskExecutor(taskExecutor())
//                .build();
//    }
//
//    @Bean
//    public TaskExecutor taskExecutor() {
//        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
//        taskExecutor.setCorePoolSize(8);
//        taskExecutor.setMaxPoolSize(8);
//        taskExecutor.setThreadNamePrefix("async-thread");
//        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
//
//        return taskExecutor;
//    }
//
//    @Bean
//    public ItemReader<String> itemReader() {
//        List<String> list = new ArrayList<>();
//        for (int i = 1; i <= 100000; i++) {
//            list.add(i + " " + UUID.randomUUID().toString());
//        }
//        return new ListItemReader<>(list);
//    }
//}
