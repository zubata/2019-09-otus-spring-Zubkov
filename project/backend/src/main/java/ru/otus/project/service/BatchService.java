package ru.otus.project.service;

public interface BatchService {

    void saveToCsv(String jobName, String path);

    void restartJob(String jobName);

}
