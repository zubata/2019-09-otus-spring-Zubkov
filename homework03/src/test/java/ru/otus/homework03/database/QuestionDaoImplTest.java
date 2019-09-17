package ru.otus.homework03.database;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework03.config.AppSettings;
import ru.otus.homework03.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тест класса QuestionDaoImpl")
@SpringBootTest
@ConfigurationProperties("application")
class QuestionDaoImplTest {

    @Autowired
    private QuestionDao questionDao;
    @MockBean
    private AppSettings appSettings;
    private List<Question> list = new ArrayList<>();;


    @BeforeEach
    void initList() throws IOException {
        InputStream inn = getClass().getClassLoader().getResourceAsStream("csv/test_en.csv");
        CSVReader rd = new CSVReader(new InputStreamReader(inn), ';');
        String[] temp;
        while ((temp = rd.readNext()) != null) {
            list.add(new Question(temp[0], temp[1]));
        }
    }

    @DisplayName(" метода getQuestion")
    @Test
    void testgetQuestions() throws IOException {
        given(appSettings.getFilename()).willReturn("csv/test_en.csv");
        List<Question> testList = questionDao.getQuestions();
        assertThat(list).usingFieldByFieldElementComparator().isEqualTo(testList);
        verify(appSettings,times(1)).getFilename();
    }

}