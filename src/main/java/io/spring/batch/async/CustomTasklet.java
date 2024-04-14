//package io.spring.batch.async;
//
//import io.spring.batch.utils.BatchUtils;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//
//public class CustomTasklet implements Tasklet {
//
//    private long sum;
//
//    @Override
//    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//        synchronized (this) {
//            for (int i = 0; i < 100000; i++) {
//                sum++;
//            }
//            System.out.printf("%s has been executed on thread %s\n", BatchUtils.extractStepName(chunkContext), Thread.currentThread().getName());
//            System.out.printf("sum : %d\n", sum);
//        }
//
//        return RepeatStatus.FINISHED;
//    }
//}