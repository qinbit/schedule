package com.pnshsh;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/jobs")
public class TriggerController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job clockJob;

    @RequestMapping("/clockJob")
    public String handle() throws Exception {
        JobExecution jobExecution = jobLauncher.run(clockJob, new JobParametersBuilder().addDate("current", new Date()).toJobParameters());
        return jobExecution.getExitStatus().getExitCode();
    }
}
