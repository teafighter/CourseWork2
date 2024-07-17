package pro.sky.java.coursework2.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.java.coursework2.domain.Question;
import pro.sky.java.coursework2.service.impl.JavaQuestionService;

public class JavaQuestionsServiceTest {
    private final QuestionService questionService = new JavaQuestionService();

    @Test // тестирование добавления вопроса из строк
    public void shouldAddQuestionFromStrings() {
        // given
        Question expectedQuestion = new Question("Question?", "Answer");
        Assertions.assertFalse(questionService.getAll().contains(expectedQuestion));
        // when
        Question actualQuestion = questionService.add("Question?", "Answer");
        // then
        Assertions.assertEquals(expectedQuestion, actualQuestion);
        Assertions.assertTrue(questionService.getAll().contains(expectedQuestion));
    }

    @Test // тестирование добавления вопроса как объекта
    public void shouldAddQuestionAsObject() {
        // given
        Question expectedQuestion = new Question("Question?", "Answer");
        Assertions.assertFalse(questionService.getAll().contains(expectedQuestion));
        // when
        Question actualQuestion = questionService.add(expectedQuestion);
        // then
        Assertions.assertEquals(expectedQuestion, actualQuestion);
        Assertions.assertTrue(questionService.getAll().contains(expectedQuestion));
    }

    @Test // тестирование удаления вопроса как объекта
    public void shouldRemoveQuestionAsObject() {
        // given
        Question expectedQuestion = new Question("Question?", "Answer");
        questionService.add(expectedQuestion);
        Assertions.assertTrue(questionService.getAll().contains(expectedQuestion));
        // when
        Question actualQuestion = questionService.remove(expectedQuestion);
        // then
        Assertions.assertEquals(expectedQuestion, actualQuestion);
        Assertions.assertFalse(questionService.getAll().contains(expectedQuestion));
    }

    @Test // тестирование удаления вопроса и уменьшение общего количества вопросов
    public void shouldRemoveQuestionAndDecreaseNumberOfQuestions() {
        // given
        Question questionToRemove = new Question("Question?", "Answer");
        questionService.add(questionToRemove);
        Assertions.assertTrue(questionService.getAll().contains(questionToRemove));
        int expectedAmountOfQuestions = 0;
        // when
        questionService.remove(questionToRemove);
        int actualAmountOfQuestions = questionService.getAll().size();
        // then
        Assertions.assertEquals(expectedAmountOfQuestions, actualAmountOfQuestions);
        Assertions.assertFalse(questionService.getAll().contains(questionToRemove));
    }

    @Test // тестирование возврата случайного вопроса
    public void shouldReturnRandomQuestion() {
        // given
        questionService.add("Question1?", "Answer1");
        questionService.add("Question2?", "Answer2");
        questionService.add("Question3?", "Answer3");
        // when
        Question actualQuestion = questionService.getRandomQuestion();
        // дальше посмотреть для себя, что вопросы действительно разные
        System.out.println(questionService.getRandomQuestion());
        System.out.println(questionService.getRandomQuestion());
        System.out.println(questionService.getRandomQuestion());
        //then
        Assertions.assertTrue(questionService.getAll().contains(actualQuestion));
    }

    @Test // проверка размера коллекции
    public void shouldReturnCollectionSize() {
        // given
        Question question1 = questionService.add("Question1?", "Answer1");
        Question question2 = questionService.add("Question2?", "Answer2");
        Question question3 = questionService.add("Question3?", "Answer3");
        int expectedSize = 3;
        // when
        int actualSize = questionService.getAll().size();
        //then
        Assertions.assertEquals(expectedSize, actualSize);
        Assertions.assertTrue(questionService.getAll().contains(question1));
        Assertions.assertTrue(questionService.getAll().contains(question2));
        Assertions.assertTrue(questionService.getAll().contains(question3));

    }
}
