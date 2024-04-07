//package io.spring.batch.async;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.integration.async.AsyncItemProcessor;
//import org.springframework.batch.integration.async.AsyncItemWriter;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Configuration
//@RequiredArgsConstructor
//public class AsyncConfiguration {
//
//    private static final String JOB_NAME = "asyncJob";
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    private final StopWatchJobListener stopWatchJobListener;
//
//    @Bean
//    public Job job() throws InterruptedException {
//        return new JobBuilder(JOB_NAME, jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(asyncStep1())
//                .listener(stopWatchJobListener)
//                .build();
//    }
//
//    @Bean
//    public Step step1() throws InterruptedException {
//        return new StepBuilder("step1", jobRepository)
//                .<String, String>chunk(100, platformTransactionManager)
//                .reader(reader())
//                .processor(customItemProcessor())
//                .writer(customItemWriter())
//                .build();
//    }
//
//    @Bean
//    public ItemWriter<String> customItemWriter() {
//        return chunk -> {
//            chunk.getItems().forEach(item -> System.out.println(LocalDateTime.now() + " " + item));
//        };
//    }
//
//    @Bean
//    public ItemProcessor<String, String> customItemProcessor() throws InterruptedException {
//        return item -> {
//            Thread.sleep(10);
//            return item.toUpperCase();
//        };
//    }
//
//    @Bean
//    public Step asyncStep1() throws InterruptedException {
//        return new StepBuilder("asyncStep1", jobRepository)
//                .<String, String>chunk(100, platformTransactionManager)
//                .reader(reader())
//                .processor(asyncItemProcessor())
//                .writer(asyncItemWriter())
//                .build();
//    }
//
//    @Bean
//    public ItemWriter asyncItemWriter() {
//        AsyncItemWriter<String> asyncItemWriter = new AsyncItemWriter<>();
//        asyncItemWriter.setDelegate(customItemWriter());
//
//        return asyncItemWriter;
//    }
//
//    @Bean
//    public AsyncItemProcessor asyncItemProcessor() throws InterruptedException {
//        AsyncItemProcessor<String, String> asyncItemProcessor = new AsyncItemProcessor<>();
//        asyncItemProcessor.setDelegate(customItemProcessor());
//        asyncItemProcessor.setTaskExecutor(new SimpleAsyncTaskExecutor());
//
//        return asyncItemProcessor;
//    }
//
//    @Bean
//    public ItemReader<String> reader() {
//        List<String> list = new ArrayList<>();
//        for (int i = 1; i <= 100000; i++) {
//            list.add(i + " "  + UUID.randomUUID().toString());
//        }
//        return new ListItemReader<>(list);
//    }
//}
