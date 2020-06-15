package ru.otus.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.otus.project.domain.Person;
import ru.otus.project.domain.Vine;
import ru.otus.project.stroge.PersonDao;
import ru.otus.project.stroge.VineDao;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {

    private final PersonDao personDao;
    private final VineDao vineDao;
    private final JavaMailSender emailSender;

    public void subscribeSender() {
        List<Person> personList = personDao.findAll();
        for (Person person : personList) {
            List<Vine> availableVines = getAvailableVines(person);
            if (availableVines.size() == 0) continue;
            StringBuilder text = new StringBuilder();
            text.append("Ваши любимые вина снова в продаже, посмотри их список:\n");
            for (Vine vine : availableVines) {
                text.append(String.format("Вино %s в продаже по цене %.2f\n" +
                                "cсылка: %s",
                        vine.getName(), vine.getCost(),vine.getUrl()));
            }
            sendMail(person.getEmail(), text.toString());
        }

    }

    //get person's favourite vines and check one by one that it is available
    private List<Vine> getAvailableVines(Person person) {
        List<Vine> availableVines = new ArrayList<>();
        List<Vine> favouriteList = person.getVine();
        for (Vine v : favouriteList) {
            Vine tempVine = vineDao.getOne(v.getId());
            if (tempVine.getIsAvailable()) {
                availableVines.add(tempVine);
            }
        }
        return availableVines;
    }

    private void sendMail(String to, String text) {
        String subject = "Ваши любимые вина снова в продаже";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }


}
