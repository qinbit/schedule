package com.pnshsh.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;


@Configuration
public class ClockJobConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Job clockJob(@Qualifier("printStep") Step step) {
        return jobs.get("myJob").start(step).build();
    }

    @Bean
    protected Step printStep(Tasklet printTimeTasklet) {
        return steps.get("step").tasklet(printTimeTasklet).build();
    }

    @Bean
    protected Tasklet printTimeTasklet() {
        return (stepContribution, chunkContext) -> {
            System.out.println(new Date());
            return RepeatStatus.FINISHED;
        };
    }


}
