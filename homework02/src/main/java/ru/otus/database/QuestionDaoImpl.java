package ru.otus.database;

import com.opencsv.CSVReader;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;

@Repository
public class QuestionDaoImpl implements QuestionDao {
    private HashMap<String, String> questionAndAnswer;
    private MessageSource ms;

    public QuestionDaoImpl(HashMap<String, String> questionAndAnswer,MessageSource ms) {
        this.questionAndAnswer = questionAndAnswer;
        this.ms = ms;
    }

    public HashMap<String, String> getQuestions() throws IOException {
        InputStream inn = getClass().getClassLoader().getResourceAsStream(ms.getMessage("source",null, Locale.getDefault()));
        CSVReader rd = new CSVReader(new InputStreamReader(inn),';');
        String[] temp;
        while ((temp = rd.readNext()) != null) {
            questionAndAnswer.put(temp[0], temp[1]);
        }
        inn.close();
        return questionAndAnswer;
    }

}
