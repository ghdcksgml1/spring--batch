//package io.spring.batch.step;
//
//import io.spring.batch.utils.BatchUtils;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.item.ExecutionContext;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.stereotype.Component;
//
//import static io.spring.batch.utils.BatchUtils.*;
//
//@Component
//public class ExecutionContextTasklet1 implements Tasklet {
//    @Override
//    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//        System.out.println(getClass() + "was executed");
//
//        ExecutionContext jobExecutionContext = extractJobExecutionContext(chunkContext);
//        ExecutionContext stepExecutionContext = extractStepExecutionContext(chunkContext);
//
//        String jobName = extractJobName(chunkContext);
//        String stepName = extractStepName(chunkContext);
//
//        if (!jobExecutionContext.containsKey("jobName")) {
//            jobExecutionContext.put("jobName", jobName);
//        }
//
//        if (!stepExecutionContext.containsKey("stepName")) {
//            stepExecutionContext.put("stepName", stepName);
//        }
//
//        System.out.println("jobName: " + jobExecutionContext.get("jobName") + ", stepName: " + stepExecutionContext.get("stepName"));
//
//        return RepeatStatus.FINISHED;
//    }
//}
