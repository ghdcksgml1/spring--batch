//package io.spring.batch.retry;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.skip.SkipLimitExceededException;
//import org.springframework.batch.core.step.skip.SkipPolicy;
//import org.springframework.batch.item.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class FaultTolerantConfiguration {
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
//                    int i = 0;
//                    @Override
//                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//                        i++;
//                        if (i == 1) {
//                            throw new IllegalArgumentException("this exception is skipped.");
//                        }
//                        return i > 4 ? null : "item" + i;
//                    }
//                })
//                .processor(new ItemProcessor<String, String>() {
//                    @Override
//                    public String process(String item) throws Exception {
//                        System.out.println("hihi====");
//                        throw new IllegalStateException("this exception is retried");
//                    }
//                })
//                .writer(System.out::println)
//                .faultTolerant()
//                    .skip(IllegalArgumentException.class)
//                    .skip(IllegalStateException.class)
//                    .skipLimit(3)
//                    .retry(IllegalStateException.class)
//                    .retryLimit(2)
//                .build();
//    }
//}
