package ru.otus.database;

import java.util.HashMap;

public interface QuestionDB {
    HashMap<String, String> getQuestions();
    void setQuestionAndAnswer(String question, String answer);
}
