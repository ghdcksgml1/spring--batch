//package io.spring.batch.retry;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.*;
//import org.springframework.batch.repeat.RepeatCallback;
//import org.springframework.batch.repeat.RepeatContext;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;
//import org.springframework.batch.repeat.support.RepeatTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class RepeatConfiguration {
//
//    private static final String JOB_NAME = "retryJob";
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public Job retryJob() {
//        return new JobBuilder(JOB_NAME, jobRepository)
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
//
//                    @Override
//                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//                        i++;
//                        return i > 3 ? null : "item" + i;
//                    }
//                })
//                .processor(new ItemProcessor<String, String>() {
//
//                    RepeatTemplate repeatTemplate = new RepeatTemplate();
//
//                    @Override
//                    public String process(String item) throws Exception {
//                        System.out.println("============== item = " + item);
//
//                        repeatTemplate.setCompletionPolicy(new SimpleCompletionPolicy(3));
//                        repeatTemplate.iterate(new RepeatCallback() {
//                            @Override
//                            public RepeatStatus doInIteration(RepeatContext context) throws Exception {
//                                System.out.println("repeatTemplate is testing");
//                                return RepeatStatus.CONTINUABLE;
//                            }
//                        });
//
//                        return item;
//                    }
//                })
//                .writer(items -> System.out.println(items))
//                .build();
//    }
//}
