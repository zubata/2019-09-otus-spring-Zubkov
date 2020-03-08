package ru.otus.project.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.project.config.IOService;
import ru.otus.project.service.ParseSiteService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommandsForParse {

    private final IOService ioService;
    private final ParseSiteService parseSiteService;

    @ShellMethod(value = "parse vine site", key = "pvs")
    public void parseVineSite() { parseSiteService.parseSite(); }
}
