package ru.otus.project.service;

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
        interceptor.setUser(name, password);
        try {
            feignForAuth.personAuth();
            ioService.output("Авторизация прошла успешно");
        } catch (FeignException e) {
            ioService.output("Авторизация неуспешна, ошибка номер " + e.status());
        }

    }

    @Override
    public void logOut() {
        interceptor.setUser(null, null);
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
}
