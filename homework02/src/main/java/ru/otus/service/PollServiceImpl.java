package ru.otus.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.MessageWrapper;
import ru.otus.database.QuestionDao;
import ru.otus.domain.Person;
import ru.otus.domain.Question;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Service
public class PollServiceImpl implements PollService {
    private final Person person;
    private final InOutService inOutService;
    private final MessageWrapper mw;
    private final QuestionDao base;
    private static final int THRESHOLD = 75;

    public PollServiceImpl(PersonService serviceGet, InOutService inOutService, MessageWrapper mw, QuestionDao base) throws IOException {
        this.person = serviceGet.getPerson();
        this.inOutService = inOutService;
        this.mw = mw;
        this.base = base;
    }

    public void testing() throws IOException {
        List<Question> questions = base.getQuestions();
        int result = 0;
        String answer;
        inOutService.output(mw.getMessage("intro.msg", person.getFirstName(), person.getSecondName()));
        for (Question question : questions) {
            inOutService.output(question.getQuestion());
            answer = inOutService.input();
            if (answer.equals(question.getAnswer())) result++;
        }
        if (questions.size() == 0) {
            inOutService.output(mw.getMessage("error.msg"));
            return;
        }
        result = result * 100 / questions.size();
        String strResult = String.valueOf(result);
        if (result > THRESHOLD) {
            inOutService.output(mw.getMessage("good.msg",strResult));
        } else {
            inOutService.output(mw.getMessage("bad.msg", strResult));
        }
    }
}
