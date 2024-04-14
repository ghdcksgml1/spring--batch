package io.spring.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CustomJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Job is Started");
        System.out.println(jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        long startTime = jobExecution.getStartTime().toInstant(ZoneOffset.UTC).getEpochSecond();
        long endTime = jobExecution.getEndTime().toInstant(ZoneOffset.UTC).getEpochSecond();

        System.out.println("총 소요시간 : " + (endTime - startTime) + " ms");
    }
}
