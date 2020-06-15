package ru.otus.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.config.IOService;
import ru.otus.project.domain.Vine;
import ru.otus.project.rest.FeignForAuth;
import ru.otus.project.rest.FeignForPerson;
import ru.otus.project.rest.MyBasicAuthRequestInterceptor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final MyBasicAuthRequestInterceptor interceptor;
    private final IOService ioService;
    private final FeignForAuth feignForAuth;
    private final FeignForPerson feignForPerson;

    @Override
    public void logIn() {
        ioService.output("Введите пользователя имя");
        String name = ioService.input();
        ioService.output("Введите пароль пользователя");
        String password = ioService.input();
        try {
            String response = feignForAuth.personAuth(String.format("grant_type=%s&username=%s&password=%s","password",name,password));
            String token = getToken(response);
            interceptor.setToken(token);
            ioService.output("Авторизация прошла успешно");
        } catch (FeignException e) {
            ioService.output("Авторизация неуспешна, ошибка номер " + e.status());
        }

    }

    @Override
    public void logOut() {
        feignForAuth.revokeToken();
        interceptor.setToken(null);
        ioService.output("Выход выполнен");
    }

    @Override
    public void addFavouriteVine() {
        ioService.output("Введите id вино, которое хотите добавить в любимые и отслеживать его");
        long id = Long.parseLong(ioService.input());
        feignForPerson.addFavouriteVine(id);
    }

    @Override
    public void deleteFavouriteVine() {
        ioService.output("Введите id вино, которое хотите удалить из любимых");
        long id = Long.parseLong(ioService.input());
        feignForPerson.deleteFavouriteVine(id);
    }

    @Override
    public void getFavouriteVines() {
        List<Vine> list = feignForPerson.getFavouriteVines();
        list.forEach(s -> ioService.output(s.toString()));
    }

    private String getToken(String response) {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.readTree(response).findValue("access_token").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
