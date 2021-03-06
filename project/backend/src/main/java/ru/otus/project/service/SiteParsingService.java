package ru.otus.project.service;

import ru.otus.project.domain.Vine;
import ru.otus.project.simplewineParse.dto.VineDto;

import java.io.IOException;
import java.util.List;

public interface SiteParsingService {

    void createVineList() throws IOException;

    List<Vine> convertDtoToEntity(List<VineDto> vineDtoList);

}
