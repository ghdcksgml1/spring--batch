package io.spring.batch.flow;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class JobExecutionDeciderConfiguration {

    private static final String JOB_NAME = "DeciderJob";

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public Job job() {
        return new JobBuilder(JOB_NAME, jobRepository)
                .start(step())
                .next(decider())
                .from(decider()).on("ODD").to(oddStep())
                .from(decider()).on("EVEN").to(evenStep())
                .end()
                .build();
    }

    @Bean
    public JobExecutionDecider decider() {
        return new FlowJobDecider();
    }

    @Bean
    public Step step() {
        return new StepBuilder("startStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("This is the start tasklet");
                    return RepeatStatus.FINISHED;
                }, platformTransactionManager)
                .build();
    }

    @Bean
    public Step evenStep() {
        return new StepBuilder("evenStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> EvenStep has executed!");
                    return RepeatStatus.FINISHED;
                }, platformTransactionManager)
                .build();
    }

    @Bean
    public Step oddStep() {
        return new StepBuilder("oddStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">> OddStep has executed!");
                    return RepeatStatus.FINISHED;
                }, platformTransactionManager)
                .build();
    }
}
