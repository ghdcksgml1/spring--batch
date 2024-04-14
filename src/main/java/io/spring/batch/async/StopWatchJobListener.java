package io.spring.batch.async;

import io.spring.batch.utils.RunningTimeFormatter;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class StopWatchJobListener implements JobExecutionListener {
    long startTime = 0;
    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("=======================");
        System.out.println("총 소요시간 : " + RunningTimeFormatter.format(System.currentTimeMillis() - startTime));
        System.out.println("=======================");
    }
}
