package ru.otus.project.service;

public interface BatchService {

    void saveToCsv(String jobName);

    void restartJob(String jobName);

}
