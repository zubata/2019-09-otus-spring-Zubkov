package ru.otus.database;

import ru.otus.domain.Question;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions() throws IOException;
}
