package ru.otus.project.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.project.service.BatchService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommandsForBatch {

    private final BatchService batchService;

    @ShellMethod(value = "save vines to file", key = "svf")
    public void saveVinesToFile() { batchService.saveToCsv("vineJob"); }

    @ShellMethod(value = "save vines to file restart", key = "svfr")
    public void restartSaveVinesToFile() { batchService.restartJob("vineJob"); }

    @ShellMethod(value = "save to file producers", key = "sfp")
    public void saveProducersToFile() {
        batchService.saveToCsv("producerJob");
    }

    @ShellMethod(value = "save to file producers restart", key = "sfpr")
    public void restartSaveProducersToFile() { batchService.restartJob("producerJob"); }
}
