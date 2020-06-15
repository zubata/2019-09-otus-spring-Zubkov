package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.feign.FeignForParseSite;

@Service
@RequiredArgsConstructor
public class ParseSiteServiceImpl implements ParseSiteService {

    private final FeignForParseSite feignParseSite;

    @Override
    public long parseSite() {
        long t1 = System.currentTimeMillis()/1000;
        feignParseSite.parseSite();
        long t2 = System.currentTimeMillis()/1000;
        return t2-t1;
    }
}
