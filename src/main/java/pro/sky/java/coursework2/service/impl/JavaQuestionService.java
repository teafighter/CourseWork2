package pro.sky.java.coursework2.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.coursework2.domain.Question;
import pro.sky.java.coursework2.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class JavaQuestionService implements QuestionService {
    private Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question questionToAdd = new Question(question, answer);
        questions.add(questionToAdd);
        return questionToAdd;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        Question questionToReturn = null;
        int randomIndex = random.nextInt(getQuestionsCollectionSize());
        int i = 0;
        for (Question question : questions) {
            if (i == randomIndex) {
                questionToReturn =  question;
            }
            i++;
        }
        return questionToReturn;
    }

    @Override
    public int getQuestionsCollectionSize() {
        return questions.size();
    }
}
