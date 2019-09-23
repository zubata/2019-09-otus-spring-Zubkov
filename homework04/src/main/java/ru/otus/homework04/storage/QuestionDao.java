package ru.otus.homework04.storage;


import ru.otus.homework04.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions() throws IOException;
}
