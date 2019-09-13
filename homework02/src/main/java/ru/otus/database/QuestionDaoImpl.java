package ru.otus.database;

import com.opencsv.CSVReader;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class QuestionDaoImpl implements QuestionDao {
    private List<Question> questionAndAnswer;
    private MessageSource ms;

    public QuestionDaoImpl(MessageSource ms) {
        this.questionAndAnswer = new ArrayList<>();
        this.ms = ms;
    }

    public List<Question> getQuestions() throws IOException {
        InputStream inn = getClass().getClassLoader().getResourceAsStream(ms.getMessage("source",null, Locale.getDefault()));
        CSVReader rd = new CSVReader(new InputStreamReader(inn),';');
        String[] temp;
        while ((temp = rd.readNext()) != null) {
            questionAndAnswer.add(new Question(temp[0],temp[1]));
        }
        inn.close();
        return questionAndAnswer;
    }

}
