//package io.spring.batch.async;
//
//import ch.qos.logback.classic.model.LoggerModel;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.partition.support.Partitioner;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Configuration
//@RequiredArgsConstructor
//public class PartitioningConfiguration {
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
//                .start(masterStep())
//                .listener(new StopWatchJobListener())
//                .build();
//    }
//
//    @Bean
//    public Step masterStep() {
//        return new StepBuilder("masterStep", jobRepository)
//                .partitioner(slaveStep().getName(), partitioner())
//                .step(slaveStep())
//                .gridSize(4)
//                .taskExecutor(new SimpleAsyncTaskExecutor())
//                .build();
//    }
//
//    @Bean
//    public Partitioner partitioner() {
//        return new ColumnRangePartitioner();
//    }
//
//    @Bean
//    public Step slaveStep() {
//        return new StepBuilder("slaveStep", jobRepository)
//                .<String, String>chunk(100, platformTransactionManager)
//                .reader(itemReader())
//                .writer(chunk -> {
//                    System.out.println(chunk);
//                })
//                .build();
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
