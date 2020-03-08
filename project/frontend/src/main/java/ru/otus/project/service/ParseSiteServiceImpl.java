package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.config.IOService;
import ru.otus.project.rest.FeignForParseSite;

@Service
@RequiredArgsConstructor
public class ParseSiteServiceImpl implements ParseSiteService {

    private final FeignForParseSite feignParseSite;
    private final IOService ioService;

    @Override
    public void parseSite() {
        long t1 = System.currentTimeMillis()/1000;
        feignParseSite.parseSite();
        long t2 = System.currentTimeMillis()/1000;
        ioService.output("Парсинг успешно отработал\n" +
                "Время работы парсин в секундах " + String.valueOf(t2-t1));
    }
}
