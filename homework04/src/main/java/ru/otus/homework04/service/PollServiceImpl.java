package ru.otus.homework04.service;

import org.springframework.stereotype.Service;
import ru.otus.homework04.domain.Person;
import ru.otus.homework04.domain.Question;
import ru.otus.homework04.config.MessageWrapper;
import ru.otus.homework04.database.QuestionDao;

import java.io.IOException;
import java.util.List;

@Service
public class PollServiceImpl implements PollService {
    private final PersonService personService;
    private final IOService IOService;
    private final MessageWrapper mw;
    private final QuestionDao base;
    private static final int THRESHOLD = 75;

    public PollServiceImpl(PersonService personService, IOService IOService, MessageWrapper mw, QuestionDao base) throws IOException {
        this.personService = personService;
        this.IOService = IOService;
        this.mw = mw;
        this.base = base;
    }

    public void testing() throws IOException {
        Person person = personService.getPerson();
        List<Question> questions = base.getQuestions();
        int result = 0;
        String answer;
        IOService.output(mw.getMessage("intro.msg", person.getFirstName(), person.getSecondName()));
        for (Question question : questions) {
            IOService.output(question.getQuestion());
            answer = IOService.input();
            if (answer.equals(question.getAnswer())) result++;
        }
        if (questions.size() == 0) {
            IOService.output(mw.getMessage("error.msg"));
            return;
        }
        result = result * 100 / questions.size();
        String strResult = String.valueOf(result);
        if (result > THRESHOLD) IOService.output(mw.getMessage("good.msg",strResult));
        else IOService.output(mw.getMessage("bad.msg", strResult));
    }

    public void testing(Person person) throws IOException {
        List<Question> questions = base.getQuestions();
        int result = 0;
        String answer;
        IOService.output(mw.getMessage("intro.msg", person.getFirstName(), person.getSecondName()));
        for (Question question : questions) {
            IOService.output(question.getQuestion());
            answer = IOService.input();
            if (answer.equals(question.getAnswer())) result++;
        }
        if (questions.size() == 0) {
            IOService.output(mw.getMessage("error.msg"));
            return;
        }
        result = result * 100 / questions.size();
        String strResult = String.valueOf(result);
        if (result > THRESHOLD) IOService.output(mw.getMessage("good.msg",strResult));
        else IOService.output(mw.getMessage("bad.msg", strResult));
    }
}