package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.stereotype.Service;
import ru.otus.project.batch.StepProducerConfig;
import ru.otus.project.batch.StepVineConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {

    private final JobLauncher jobLauncher;
    private final JobOperator jobOperator;
    private final StepProducerConfig stepProducer;
    private final StepVineConfig stepVine;
    private final List<Job> jobs;
    private List<JobExecution> jobExecutionList = new ArrayList<>();
    private final Logger log = LoggerFactory.getLogger("BatchService");

    @Override
    public void saveToCsv(String jobName, String path) {
        setFileName(path);
        Optional<Job> job = jobs.stream().filter(s -> s.getName().equals(jobName)).findFirst();
        if (job.isPresent()) {
            try {
                if (checkJob(jobName, "COMPLETED") != -1) {
                    jobOperator.startNextInstance(jobName);
                } else {
                    jobExecutionList.add(jobLauncher.run(job.get(), new JobParameters()));
                }
            } catch (Exception e) {
                log.error(e.toString());
                e.printStackTrace();
            }
        } else {
            log.info(String.format("Job для %s не найдена", jobName));
        }
    }

    @Override
    public void restartJob(String jobName) {
        long jobId = checkJob(jobName, "FAILED");
        try {
            jobOperator.restart(jobId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long checkJob(String jobName, String status) {
        for (JobExecution je : jobExecutionList) {
            String tempJobName = je.getJobInstance().getJobName();
            String tempStatus = je.getStatus().name();
            if (tempJobName.equals(jobName) && tempStatus.equals(status)) {
                return je.getJobId();
            }
        }
        return -1;
    }

    private void setFileName(String filePath) {
        stepVine.setResource(filePath + "/Vine.csv");
        stepProducer.setResource(filePath + "/Producer.csv");
    }
}
