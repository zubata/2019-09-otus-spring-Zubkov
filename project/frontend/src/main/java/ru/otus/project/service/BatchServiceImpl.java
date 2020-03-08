package ru.otus.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.project.config.IOService;
import ru.otus.project.exception.EmptyDirPath;
import ru.otus.project.rest.FeignForBatch;

import java.io.File;

@Service
public class BatchServiceImpl implements BatchService {

    private final FeignForBatch feignBatch;
    private final IOService ioService;
    private String folderpath;

    @Autowired
    public BatchServiceImpl(FeignForBatch feignBatch, IOService ioService, @Value("${batch.file.path}") String folderpath) {
        this.feignBatch = feignBatch;
        this.ioService = ioService;
        this.folderpath = folderpath;
    }

    @Override
    public void saveToCsv(String jobName) {
        if (folderpath.equals("")) throw new EmptyDirPath();
        if (jobName.equals("vineJob")) feignBatch.saveVinesToCsv(folderpath);
        else if (jobName.equals("producerJob")) feignBatch.saveProducerToCsv(folderpath);
        ioService.output("Данные сохранены в файл");
    }

    @Override
    public void restartJob(String jobName) {
        if (jobName.equals("vineJob")) feignBatch.restartVinesToCsv();
        else if (jobName.equals("producerJob")) feignBatch.restartProducerToCsv();
        ioService.output("Рестарт сохранения в файл завершён");
    }

}
