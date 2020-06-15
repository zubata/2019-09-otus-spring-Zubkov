package ru.otus.project.rest;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.project.domain.History;
import ru.otus.project.service.HistoryService;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Controller
public class ControllerForHistory {

    private final HistoryService historyService;

    @GetMapping("/history/vine")
    public String showVineHistory(@RequestParam long id, Model model) {
        List<History> list = historyService.getVineHistoryById(id);
        model.addAttribute("vine",list.get(0).getVine());
        model.addAttribute("prices", list.stream().map(History::getPrice).collect(Collectors.toList()));
        model.addAttribute("dates",list.stream().map(History::getDate).collect(Collectors.toList()));
        model.addAttribute("history",list);
        return "history";
    }

}
