package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import ru.otus.project.feign.FeignForBatch;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {

    private final FeignForBatch feignBatch;

    @Override
    public ByteArrayResource saveToCsv(String jobName) {
        if (jobName.equals("vineJob")) {
            return feignBatch.saveVinesToCsv();
        } else if (jobName.equals("producerJob")) return feignBatch.saveProducerToCsv();
        return null;
    }

}
