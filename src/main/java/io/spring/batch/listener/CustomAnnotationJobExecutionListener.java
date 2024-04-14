package io.spring.batch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;

import java.time.ZoneOffset;

public class CustomAnnotationJobExecutionListener {
    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Job is Started");
        System.out.println(jobExecution.getJobInstance().getJobName());
    }

    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        long startTime = jobExecution.getStartTime().toInstant(ZoneOffset.UTC).getEpochSecond();
        long endTime = jobExecution.getEndTime().toInstant(ZoneOffset.UTC).getEpochSecond();

        System.out.println("총 소요시간 : " + (endTime - startTime) + " ms");
    }
}
