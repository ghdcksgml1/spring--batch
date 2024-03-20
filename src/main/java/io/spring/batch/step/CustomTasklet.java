package io.spring.batch.step;

import io.spring.batch.utils.BatchUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CustomTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        String stepName = BatchUtils.extractStepName(chunkContext);
        String jobName = BatchUtils.extractJobName(chunkContext);

        System.out.println("stepName = " + stepName);
        System.out.println("jobName = " + jobName);

        return RepeatStatus.FINISHED;
    }
}
