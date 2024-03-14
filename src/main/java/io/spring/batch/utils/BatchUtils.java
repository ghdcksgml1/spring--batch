package io.spring.batch.utils;

import lombok.experimental.UtilityClass;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ExecutionContext;

@UtilityClass
public class BatchUtils {
    public String extractJobName(ChunkContext chunkContext) {
        return chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getJobName();
    }
    public String extractStepName(ChunkContext chunkContext) {
        return chunkContext.getStepContext().getStepExecution().getStepName();
    }

    public ExecutionContext extractJobExecutionContext(ChunkContext chunkContext) {
        return chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
    }

    public ExecutionContext extractStepExecutionContext(ChunkContext chunkContext) {
        return chunkContext.getStepContext().getStepExecution().getExecutionContext();
    }
}
