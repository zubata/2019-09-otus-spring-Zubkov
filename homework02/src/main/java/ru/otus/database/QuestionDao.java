package ru.otus.database;

import java.io.IOException;
import java.util.HashMap;

public interface QuestionDao {
    HashMap<String, String> getQuestions() throws IOException;
}
