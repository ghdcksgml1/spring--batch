//package io.spring.batch;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.Map;
//
//@Configuration
//@RequiredArgsConstructor
//public class JobParameterConfiguration {
//
//    private static final String JOB_PARAMETER = "JobParameter";
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public Job JobParameter() {
//        return new JobBuilder(JOB_PARAMETER, jobRepository)
//                .start(JobParameterStep1())
//                .next(JobParameterStep2())
//                .build();
//    }
//
//    @Bean
//    public Step JobParameterStep1() {
//        return new StepBuilder(JOB_PARAMETER + "Step1", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    JobParameters jobParameters = getParameters(contribution);
//                    jobParameters.getString("name");
//                    jobParameters.getLong("seq");
//                    jobParameters.getDate("date");
//                    jobParameters.getDouble("age");
//                    System.out.println(JOB_PARAMETER);
//                    Map<String, Object> jobParameters1 = chunkContext.getStepContext().getJobParameters();
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//
//    @Bean
//    public Step JobParameterStep2() {
//        return new StepBuilder(JOB_PARAMETER + "Step2", jobRepository)
//                .tasklet((contribution, chunkContext) -> {
//                    System.out.println(JOB_PARAMETER);
//                    return RepeatStatus.FINISHED;
//                }, platformTransactionManager)
//                .build();
//    }
//
//    private JobParameters getParameters(StepContribution stepContribution) {
//        return stepContribution.getStepExecution().getJobExecution().getJobParameters();
//    }
//}
