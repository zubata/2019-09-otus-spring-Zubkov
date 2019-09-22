package ru.otus.homework03.storage;


import ru.otus.homework03.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions() throws IOException;
}
