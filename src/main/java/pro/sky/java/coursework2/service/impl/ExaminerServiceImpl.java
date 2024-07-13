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

    private final Set<Question> examQuestions = new HashSet<>();

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getQuestionsCollectionSize()) {
            throw new NotEnoughQuestionsException();
        }
        while (examQuestions.size() < amount) {
            examQuestions.add(questionService.getRandomQuestion());
        }
        return examQuestions;
    }
}
