//package io.spring.batch.chunk;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.Chunk;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//public class ChunkOrientedTaskletConfiguration {
//    private static final String JOB_NAME = "chunkJob";
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public Job chunkJob() {
//        return new JobBuilder(JOB_NAME, jobRepository)
//                .start(step1())
//                .next(step2())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return new StepBuilder("step1", jobRepository)
//                .<String, String>chunk(5, platformTransactionManager)
//                .reader(new ListItemReader<>(List.of("item1", "item2", "item3", "item4", "item5")))
//                .processor(new ItemProcessor<String, String>() {
//
//                    @Override
//                    public String process(String item) throws Exception {
//                        return "my " + item;
//                    }
//                })
//                .writer(new ItemWriter<String>() {
//                    @Override
//                    public void write(Chunk<? extends String> chunk) throws Exception {
//                        chunk.getItems().forEach(System.out::println);
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return new StepBuilder("step2", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step2 has executed!");
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//}
