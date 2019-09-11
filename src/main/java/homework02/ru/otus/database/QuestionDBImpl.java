package homework02.ru.otus.database;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class QuestionDBImpl implements QuestionDB {
    private HashMap<String, String> questionAndAnswer;

    public QuestionDBImpl(HashMap<String, String> questionAndAnswer) {
        this.questionAndAnswer = questionAndAnswer;
    }

    public void setQuestionAndAnswer(String question, String answer) {
        questionAndAnswer.put(question,answer);
    }

    public HashMap<String, String> getQuestions() {
        return questionAndAnswer;
    }

}
