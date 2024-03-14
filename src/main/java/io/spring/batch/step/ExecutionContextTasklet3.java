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
//@Component
//public class ExecutionContextTasklet3 implements Tasklet {
//    @Override
//    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//        System.out.println(getClass() + "was executed");
//
//        ExecutionContext jobExecutionContext = BatchUtils.extractJobExecutionContext(chunkContext);
//
//        if (!jobExecutionContext.containsKey("name")) {
//            jobExecutionContext.put("name", "user1");
//            throw new RuntimeException("step3 was failed");
//        }
//        return RepeatStatus.FINISHED;
//    }
//}
