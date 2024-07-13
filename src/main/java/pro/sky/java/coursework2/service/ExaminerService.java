package pro.sky.java.coursework2.service;

import pro.sky.java.coursework2.domain.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
