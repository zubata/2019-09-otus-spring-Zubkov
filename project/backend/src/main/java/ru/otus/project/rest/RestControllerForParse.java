package ru.otus.project.rest;

import lombok.RequiredArgsConstructor;
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

    @GetMapping("/parse")
    public ResponseEntity<Void> parseVineSite() throws IOException {
        vineParsing.createVineList();
        subscribeService.subscribeSender();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
