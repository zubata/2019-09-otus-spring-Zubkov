package ru.otus.project.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.project.service.BatchService;
import ru.otus.project.service.ProducerService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommandsForProducer {

    private final ProducerService producerService;

    @ShellMethod(value = "get producers all", key = "gpa")
    public void getAllProducerList() { producerService.getList(); }

    @ShellMethod(value = "get producer by name", key = "gpn")
    public void getProducerByName() { producerService.getByName();}

    @ShellMethod(value = "get producer by id", key = "gpi")
    public void getProducerById() { producerService.getById();}

    @ShellMethod(value = "delete producer by id", key = "dpi")
    public void deleteById() { producerService.deleteById(); }

    @ShellMethod(value = "delete producer all", key = "dpa")
    public void deleteAllProducer() { producerService.deleteAll(); }
}
