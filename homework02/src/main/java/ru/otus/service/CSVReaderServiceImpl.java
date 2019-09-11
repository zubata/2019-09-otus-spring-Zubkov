package ru.otus.service;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.database.QuestionDB;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class CSVReaderServiceImpl implements CSVReaderService {

    public CSVReaderServiceImpl(@Value("${sourcefile}") String filename, QuestionDB questionDB) throws IOException {
        InputStream inn = getClass().getClassLoader().getResourceAsStream(filename);
        CSVReader rd = new CSVReader(new InputStreamReader(inn),';');
        String[] temp = rd.readNext();
        while ((temp = rd.readNext()) != null) {
            questionDB.setQuestionAndAnswer(temp[0], temp[1]);
        }
        rd.close();
        inn.close();
    }

}
