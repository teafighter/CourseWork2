package pro.sky.java.coursework2.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.coursework2.domain.Question;
import pro.sky.java.coursework2.exceptions.NotEnoughQuestionsException;
import pro.sky.java.coursework2.service.ExaminerService;
import pro.sky.java.coursework2.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount < 0 ) {
            throw new RuntimeException("Количество вопросов не может быть отрицательным");
        }
        if (amount > questionService.getAll().size()) {
            throw new NotEnoughQuestionsException("Недостаточно вопросов в базе");
        }
        Set<Question> examQuestions = new HashSet<>();
        while (examQuestions.size() < amount) {
            examQuestions.add(questionService.getRandomQuestion());
        }
        return examQuestions;
    }
}
