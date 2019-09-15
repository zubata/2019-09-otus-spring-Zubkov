package ru.otus.database;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;
import ru.otus.config.AppSettings;
import ru.otus.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {
    private List<Question> questionAndAnswer;
    private final AppSettings appSettings;

    public QuestionDaoImpl(AppSettings appSettings) {
        this.appSettings = appSettings;
        this.questionAndAnswer = new ArrayList<>();
    }

    public List<Question> getQuestions() throws IOException {
        InputStream inn = getClass().getClassLoader().getResourceAsStream(appSettings.getFilename());
        CSVReader rd = new CSVReader(new InputStreamReader(inn),';');
        String[] temp;
        while ((temp = rd.readNext()) != null) {
            questionAndAnswer.add(new Question(temp[0],temp[1]));
        }
        inn.close();
        return questionAndAnswer;
    }

}
