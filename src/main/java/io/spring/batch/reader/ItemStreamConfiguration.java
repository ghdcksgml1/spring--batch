//package io.spring.batch.reader;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.Chunk;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//@Configuration
//@RequiredArgsConstructor
//public class ItemStreamConfiguration {
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
//                .<String, String>chunk(5, platformTransactionManager)
//                .reader(itemReader())
//                .processor(new ItemProcessor<String, String>() {
//                    @Override
//                    public String process(String item) throws Exception {
//                        System.out.println("processing = " + item);
//                        return item;
//                    }
//                })
//                .writer(itemWriter())
//                .build();
//    }
//
//    public CustomItemStreamReader itemReader() {
//        int itemCount = 10;
//        List<String> items = new ArrayList<>(itemCount);
//
//        for (int i = 0; i < itemCount; i++) {
//            items.add(String.valueOf(i));
//        }
//
//        return new CustomItemStreamReader(items);
//    }
//
//    public ItemWriter<? super String> itemWriter() {
//        return new CustomItemStreamWriter();
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
