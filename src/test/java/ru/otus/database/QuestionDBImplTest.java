package ru.otus.database;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class QuestionDBImplTest {

    @Test
    public void setQuestionAndAnswer() {
        QuestionDB qDB = new QuestionDBImpl(new HashMap<String, String>());
        qDB.setQuestionAndAnswer("Сколько яблок?","5");
        HashMap<String,String> testmap = qDB.getQuestions();
        Assert.assertEquals(testmap.get("Сколько яблок?"),"5");
    }

}