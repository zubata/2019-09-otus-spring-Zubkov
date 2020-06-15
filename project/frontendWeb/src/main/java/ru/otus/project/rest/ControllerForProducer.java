package ru.otus.project.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.project.domain.Producer;
import ru.otus.project.service.ProducerService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ControllerForProducer {

    private final ProducerService producerService;

    @GetMapping("/producer")
    public String showAllProducers(Model model) {
        List<Producer> list = producerService.getList();
        model.addAttribute("producer",list);
        return "producer";
    }

}
