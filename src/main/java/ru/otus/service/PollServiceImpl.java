package ru.otus.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.database.QuestionDao;
import ru.otus.domain.Person;
import ru.otus.domain.Question;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class PollServiceImpl implements PollService {
    private List<Question> questions;
    private Person person;
    private InOutService inOutService;
    private MessageSource ms;
    private final int threshold = 75;

    public PollServiceImpl(QuestionDao base, PersonService serviceGet, InOutService inOutService, MessageSource ms) throws IOException {
        this.questions = base.getQuestions();
        this.person = serviceGet.getPerson();
        this.inOutService = inOutService;
        this.ms = ms;
    }

    public void testing() throws IOException {
        int result = 0;
        String answer;
        inOutService.output(ms.getMessage("intro.msg", new String[]{person.getFirstName(), person.getSecondName()}, Locale.getDefault()));
        for (Question question : questions) {
            inOutService.output(question.getQuestion());
            answer = inOutService.input();
            if (answer.equals(question.getAnswer())) result++;
        }
        if (questions.size() == 0) {
            inOutService.output(ms.getMessage("error.msg", null, Locale.getDefault()));
            return;
        }
        result = result * 100 / questions.size();
        if (result > threshold) {
            String strResult = String.valueOf(result);
            inOutService.output(ms.getMessage("good.msg", new String[]{strResult}, Locale.getDefault()));
        } else {
            String strResult = String.valueOf(result);
            inOutService.output(ms.getMessage("bad.msg", new String[]{strResult}, Locale.getDefault()));
        }
    }
}
