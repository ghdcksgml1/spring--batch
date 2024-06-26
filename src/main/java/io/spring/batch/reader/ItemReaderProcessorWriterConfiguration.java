//package io.spring.batch.reader;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//public class ItemReaderProcessorWriterConfiguration {
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public Job job() {
//        return new JobBuilder("job", jobRepository)
//                .start(step1())
//                .next(step2())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return new StepBuilder("step1", jobRepository)
//                .<Customer, Customer>chunk(2, platformTransactionManager)
//                .reader(itemReader())
//                .processor(itemProcessor())
//                .writer(itemWriter())
//                .build();
//    }
//
//    @Bean
//    public ItemReader<Customer> itemReader() {
//        return new CustomItemReader(List.of(new Customer("user1"), new Customer("user2"), new Customer("user3")));
//    }
//
//    @Bean
//    public ItemProcessor<? super Customer, ? extends Customer> itemProcessor() {
//        return new CustomItemProcessor();
//    }
//
//    @Bean
//    public ItemWriter<Customer> itemWriter() {
//        return new CustomItemWriter();
//    }
//
//    @Bean
//    public Step step2() {
//        return new StepBuilder("step2", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("Step2 has executed!");
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//}
