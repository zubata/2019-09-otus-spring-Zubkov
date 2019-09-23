package ru.otus.homework04.storage;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework04.config.AppSettings;
import ru.otus.homework04.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Тест класса QuestionDaoImpl")
@SpringBootTest
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
    void getQuestions() throws IOException {
        given(appSettings.getFilename()).willReturn("csv/test_en.csv");
        List<Question> testList = questionDao.getQuestions();
        assertThat(list).usingFieldByFieldElementComparator().isEqualTo(testList);
        verify(appSettings,times(1)).getFilename();
    }

}