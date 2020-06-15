package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.otus.project.domain.Person;
import ru.otus.project.domain.Vine;
import ru.otus.project.exception.NotFoundEntity;
import ru.otus.project.stroge.PersonDao;
import ru.otus.project.stroge.VineDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;
    private final VineDao vineDao;


    @Override
    public void addFavouriteVine(long id) {
        Vine tempVine = vineDao.findById(id);
        if (tempVine == null) throw new NotFoundEntity("Вино", String.valueOf(id));
        Person currentPerson = getPerson();
        currentPerson.getVine().add(tempVine);
        personDao.save(currentPerson);
    }

    @Override
    public void deleteFavouriteVine(long id) {
        Person currentPerson = getPerson();
        currentPerson.getVine().removeIf(s -> s.getId() == id);
        personDao.save(currentPerson);
    }

    @Override
    public void deleteAllFavouriteVine() {
        Person currentPerson = getPerson();
        currentPerson.setVine(null);
        personDao.save(currentPerson);
    }

    @Override
    public List<Vine> getFavouriteVines() {
        Person currentPerson = getPerson();
        return currentPerson.getVine();
    }

    private Person getPerson() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String username = (String) authentication.getPrincipal();
        return personDao.getByUsername(username).get();
    }
}
