//package io.spring.batch.step;
//
//import io.spring.batch.utils.BatchUtils;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ExecutionContext;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.Arrays;
//
//@Configuration
//@RequiredArgsConstructor
//public class TaskletStepConfiguration {
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public Job batchJob() {
//        return new JobBuilder("batchJob", jobRepository)
//                .start(taskStep())
//                .next(chunkStep())
//                .build();
//    }
//
//    @Bean
//    public Step taskStep() {
//        return new StepBuilder("taskStep", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    ExecutionContext stepExecutionContext = BatchUtils.extractStepExecutionContext(chunkContext);
//                    final String key = "count";
//                    if (!stepExecutionContext.containsKey(key)) {
//                        stepExecutionContext.putInt(key, 1);
//                    } else {
//                        int value = stepExecutionContext.getInt(key);
//                        stepExecutionContext.putInt(key, ++value);
//                    }
//                    System.out.println("key = " + stepExecutionContext.getInt(key));
//                    System.out.println("step was executed.");
//                    if (stepExecutionContext.getInt(key) < 3) {
//                        return RepeatStatus.CONTINUABLE;
//                    } else {
//                        return RepeatStatus.FINISHED;
//                    }
//                }, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step chunkStep() {
//        return new StepBuilder("chunkStep", jobRepository)
//                .<String, String>chunk(10, platformTransactionManager)
//                .reader(new ListItemReader<>(Arrays.asList("item1", "item2", "item3", "item4", "item5")))
//                .processor(item -> item.toUpperCase())
//                .writer(items -> items.forEach(System.out::println))
//                .build();
//    }
//}
