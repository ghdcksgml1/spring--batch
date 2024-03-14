//package io.spring.batch.step;
//
//import io.spring.batch.utils.BatchUtils;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.stereotype.Component;
//
//import static io.spring.batch.utils.BatchUtils.*;
//
//@Component
//public class ExecutionContextTasklet4 implements Tasklet {
//    @Override
//    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//        System.out.println("name: " + extractJobExecutionContext(chunkContext).get("name"));
//        System.out.println(getClass() + "was executed");
//        return RepeatStatus.FINISHED;
//    }
//}
