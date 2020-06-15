package ru.otus.project.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.otus.project.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ControllerForPerson {

    private final PersonService personService;

    @GetMapping("/person/list")
    public String getFavouriteVines(Principal principal, Model model) {
        model.addAttribute("vine",personService.getFavouriteVines());
        return "favvines";
    }

    @GetMapping("/person/vine/del")
    public String deleteFavouriteVine(@RequestParam long id, HttpServletRequest request) {
        personService.deleteFavouriteVine(id);
        return "redirect:" + request.getHeader("referer")+"person/list";
    }

    @GetMapping("/person/vine/all")
    public String deleteFavouriteVine(HttpServletRequest request) {
        personService.deleteAllFavouriteVine();
        return "redirect:" + request.getHeader("referer")+"person/list";
    }


    @GetMapping("/person/vine/add")
    @ResponseStatus(HttpStatus.OK)
    public void addFavouriteVine(@RequestParam long id) {
        personService.addFavouriteVine(id);
    }

}
