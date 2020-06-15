package ru.otus.project.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.service.SiteParsingService;
import ru.otus.project.service.SubscribeService;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class RestControllerForParse {

    private final SiteParsingService vineParsing;
    private final SubscribeService subscribeService;
    private final Logger log = LoggerFactory.getLogger("RestControllerForParse");

    @GetMapping("/parse")
    public ResponseEntity<Void> parseVineSite() throws IOException {
        long t1 = System.currentTimeMillis()/1000;
        vineParsing.createVineList();
        long t2 = System.currentTimeMillis()/1000;
        log.info("Парсинг успешно отработал\n" +
                "Время работы парсин в секундах " + String.valueOf(t2-t1));
        subscribeService.subscribeSender();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
