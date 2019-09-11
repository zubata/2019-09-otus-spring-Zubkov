package ru.otus.service;

import ru.otus.domain.Person;
import org.springframework.stereotype.Service;
import ru.otus.database.QuestionDBImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class ServicePollImpl implements ServicePoll {
    public HashMap<String,String> questions;

    public ServicePollImpl(QuestionDBImpl base) {
        questions=base.getQuestions();
    }

    public void testing(Person person) throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        int result = 0;
        String answer;
        System.out.println(String.format("Тест проходит: %s %s",person.getFirstName(),person.getSecondName()));
        for (Map.Entry<String,String> s : questions.entrySet()) {
            System.out.println(s.getKey());
            answer=rd.readLine();
            if(answer.equals(s.getValue())) result++;
        }
        result = result*100/questions.size();
        if(result>75) System.out.println(String.format("Тест пройден \nРезультат: %d%%",result));
        else System.out.println(String.format("Тест не пройден \nРезультат: %d%%",result));
        rd.close();
    }
}
