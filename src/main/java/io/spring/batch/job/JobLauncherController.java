//package io.spring.batch.job;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
//import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
//@RestController
//@RequiredArgsConstructor
//public class JobLauncherController {
//
//    private final Job job;
//    private final DefaultBatchConfiguration defaultBatchConfiguration;
//
//    @PostMapping("/batch")
//    public String launch(@RequestBody Member member) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("id", member.getId())
//                .addDate("date", new Date())
//                .toJobParameters();
//
//        TaskExecutorJobLauncher jobLauncher = (TaskExecutorJobLauncher) defaultBatchConfiguration.jobLauncher();
//        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
//        jobLauncher.run(job, jobParameters);
//
//        return "batch completed";
//    }
//
//    @Getter
//    private static class Member {
//        private String id;
//    }
//}