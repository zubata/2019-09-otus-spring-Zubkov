package ru.otus.project.service;

import java.io.File;

public interface BatchService {

    File saveToCsv(String jobName);

    void restartJob(String jobName);

}
