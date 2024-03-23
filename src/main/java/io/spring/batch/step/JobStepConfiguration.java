//package io.spring.batch.step;
//
//import io.spring.batch.utils.BatchUtils;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class JobStepConfiguration {
//
//    private static final String JOB_NAME = "parentJob";
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public Job parentJob() {
//        return new JobBuilder(JOB_NAME, jobRepository)
//                .start(stepJob(null))
//                .next(step2())
//                .build();
//    }
//
//    @Bean
//    public Step stepJob(JobLauncher jobLauncher) {
//        return new StepBuilder("stepJob", jobRepository)
//                .job(childJob())
//                .launcher(jobLauncher)
//                .parametersExtractor(jobParametersExtractor())
//                .listener(new StepExecutionListener() {
//                    @Override
//                    public void beforeStep(StepExecution stepExecution) {
//                        stepExecution.getExecutionContext().putString("name", "chuck2");
//                    }
//
//                    @Override
//                    public ExitStatus afterStep(StepExecution stepExecution) {
//                        return StepExecutionListener.super.afterStep(stepExecution);
//                    }
//                })
//                .build();
//    }
//
//    private DefaultJobParametersExtractor jobParametersExtractor() {
//        DefaultJobParametersExtractor extractor = new DefaultJobParametersExtractor();
//        extractor.setKeys(new String[]{"name"});
//        return extractor;
//    }
//
//    @Bean
//    public Job childJob() {
//        return new JobBuilder("childJob", jobRepository)
//                .start(step1())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return new StepBuilder("step1", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step1 was executed " + BatchUtils.extractJobParameters(chunkContext).getString("name"));
//                    System.out.println("step1 was executed " + chunkContext.getStepContext().getStepExecution().getJobParameters().getString("name"));
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step step2() {
//        return new StepBuilder("step2", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println("step2 was executed");
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//}
