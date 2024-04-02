package io.spring.batch.retry;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class RetryConfiguration {

    private static final String JOB_NAME = "retryJob";

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public Job job() {
        return new JobBuilder(JOB_NAME, jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .<String, String>chunk(5, platformTransactionManager)
                .reader(reader())
                .processor(processor())
                .writer(System.out::println)
                .faultTolerant()
                    .skip(RetryableException.class)
                    .skipLimit(2)
                    .retryLimit(2)
                    .retryPolicy(retryPolicy())
                .build();
    }

    @Bean
    public ItemProcessor<? super String, String> processor() {
        return new RetryItemProcessor();
    }

    @Bean
    public ListItemReader<String> reader() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            items.add(String.valueOf(i));
        }
        return new ListItemReader<>(items);
    }

    @Bean
    public RetryPolicy retryPolicy() {
        Map<Class<? extends Throwable>, Boolean> exceptionClass = new HashMap<>();
        exceptionClass.put(RetryableException.class, true);

        return new SimpleRetryPolicy(2, exceptionClass);
    }
}
