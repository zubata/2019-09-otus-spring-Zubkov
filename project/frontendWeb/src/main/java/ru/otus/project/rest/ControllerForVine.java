package ru.otus.project.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.project.domain.Vine;
import ru.otus.project.service.PersonService;
import ru.otus.project.service.VineService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ControllerForVine {

    private final VineService vineService;
    private final PersonService personService;

    @GetMapping("/vine/all")
    public String showVines(Model model) {
        model.addAttribute("vine", vineService.getList());
        model.addAttribute("count", vineService.getList().size());
        model.addAttribute("favines", personService.getFavouriteVines());
        return "vine";
    }

    @GetMapping("/vine")
    public String showVines(@RequestParam(defaultValue = "1") int page, Model model) {
        Page<Vine> pageble = vineService.getPageList(page - 1);
        model.addAttribute("vine", pageble.getContent());
        model.addAttribute("lastpage", pageble.getTotalPages());
        model.addAttribute("initpage", page < 10 ? 1 : (page / 10) * 10);
        model.addAttribute("count", pageble.getTotalElements());
        model.addAttribute("page", page);
        model.addAttribute("favines", personService.getFavouriteVines());
        return "vine";
    }

    @GetMapping("/vine/producer{id}")
    public String showVinesByProducer(@PathVariable long id, Model model) {
        List<Vine> vineList = vineService.getByProducer(id);
        model.addAttribute("vine", vineList);
        model.addAttribute("favines", personService.getFavouriteVines());
        model.addAttribute("producer", vineList.get(0).getProducer().getName());
        return "vine";
    }

    @GetMapping("/vine/del{id}")
    public String deleteVine(@PathVariable long id, Model model) {
        vineService.deleteById(id);
        model.addAttribute("id", id);
        return "vinedelete";
    }

    @GetMapping("/vine/del")
    public String deleteAllVines(Model model) {
        vineService.deleteAll();
        return "vinedelete";
    }
}
