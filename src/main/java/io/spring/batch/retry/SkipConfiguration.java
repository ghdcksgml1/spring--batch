//package io.spring.batch.retry;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.skip.LimitCheckingItemSkipPolicy;
//import org.springframework.batch.core.step.skip.SkipPolicy;
//import org.springframework.batch.item.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@RequiredArgsConstructor
//public class SkipConfiguration {
//
//    private static final String JOB_NAME = "retryJob";
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public Job job() {
//        return new JobBuilder(JOB_NAME, jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(step1())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return new StepBuilder("step1", jobRepository)
//                .<String, String>chunk(5, platformTransactionManager)
//                .reader(new ItemReader<String>() {
//
//                    int i = 0;
//
//                    @Override
//                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//                        i++;
//                        System.out.println("ItemReader : " + i);
//                        if (i == 3) {
//                            throw new SkippableException("skip");
//                        }
//                        return i > 20 ? null : String.valueOf(i);
//                    }
//                })
//                .processor(itemProcessor())
//                .writer(itemWriter())
//                .faultTolerant()
//                    .skipPolicy(limitCheckingItemSkipPolicy())
//                .build();
//    }
//
//    @Bean
//    public SkipPolicy limitCheckingItemSkipPolicy() {
//        Map<Class<? extends Throwable>, Boolean> exceptionClass = new HashMap<>();
//        exceptionClass.put(SkippableException.class, true);
//
//        return new LimitCheckingItemSkipPolicy(4, exceptionClass);
//    }
//
//    @Bean
//    public ItemProcessor<String, String> itemProcessor() {
//        return new SkipItemProcessor();
//    }
//
//    @Bean
//    public ItemWriter<? super String> itemWriter() {
//        return new SkipItemWriter();
//    }
//}
