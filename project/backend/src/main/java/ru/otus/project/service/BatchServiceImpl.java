package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.project.batch.StepProducerConfig;
import ru.otus.project.batch.StepVineConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BatchServiceImpl implements BatchService {

    private final JobLauncher jobLauncher;
    private final JobOperator jobOperator;
    private final StepProducerConfig stepProducer;
    private final StepVineConfig stepVine;
    private final List<Job> jobs;
    private List<JobExecution> jobExecutionList;
    private final Logger log;
    private final String folderpath;

    @Autowired
    public BatchServiceImpl(JobLauncher jobLauncher, JobOperator jobOperator, StepProducerConfig stepProducer, StepVineConfig stepVine, List<Job> jobs, @Value("${batch.file.path}") String folderpath) {
        this.jobLauncher = jobLauncher;
        this.jobOperator = jobOperator;
        this.stepProducer = stepProducer;
        this.stepVine = stepVine;
        this.jobs = jobs;
        jobExecutionList = new ArrayList<>();
        log = LoggerFactory.getLogger("BatchService");
        this.folderpath = folderpath;
    }

    @Override
    public File saveToCsv(String jobName) {
        setFileName(folderpath);
        Optional<Job> job = jobs.stream().filter(s -> s.getName().equals(jobName)).findFirst();
        if (job.isPresent()) {
            try {
                if (checkJob(jobName, "COMPLETED") != -1) {
                    jobOperator.startNextInstance(jobName);
                } else {
                    jobExecutionList.add(jobLauncher.run(job.get(), new JobParameters()));
                }
                return new File(folderpath + (jobName.equals("vineJob") ? "/Vine.csv" : "/Producer.csv"));
            } catch (Exception e) {
                log.error(e.toString());
                e.printStackTrace();
            }
        } else {
            log.info(String.format("Job для %s не найдена", jobName));
        }
        return null;
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
