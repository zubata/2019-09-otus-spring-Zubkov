package ru.otus.project.service;

import org.springframework.core.io.ByteArrayResource;

public interface BatchService {

    ByteArrayResource saveToCsv(String jobName);

}
