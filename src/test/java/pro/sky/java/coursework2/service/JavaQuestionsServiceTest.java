package pro.sky.java.coursework2.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.java.coursework2.domain.Question;
import pro.sky.java.coursework2.service.impl.JavaQuestionService;

public class JavaQuestionsServiceTest {
    private final QuestionService questionService = new JavaQuestionService();


    public void init() { // вспомогательный метод для создания тестовой коллекции вопросов
        // не используется аннотация @BeforeEach, так как не во всех тестах нужна непустая коллекция
        Question question1 = questionService.add("Какой самый строгий модификатор доступа в Java?", "private");
        Question question2 = questionService.add("Сколько бит занимает в памяти переменная типа long?", "64");
        Question question3 = new Question("Какой принцип ООП предназначен для управления доступом к данным извне?", "Инкапсуляция");
        questionService.add(question3);
        Question question4 = questionService.add("Question?", "Answer");
    }

    @Test // тестирование добавления вопроса из строк
    public void shouldAddQuestionFromStrings() {
        // given
        Question expectedQuestion = new Question("Question?", "Answer");
        init();
        // when
        Question actualQuestion = questionService.add("Question?", "Answer");
        // then
        Assertions.assertEquals(expectedQuestion, actualQuestion);
    }

    @Test // тестирование добавления вопроса как объекта
    public void shouldAddQuestionAsObject() {
        // given
        Question expectedQuestion = new Question("Question?", "Answer");
        init();
        // when
        Question actualQuestion = questionService.add(expectedQuestion);
        // then
        Assertions.assertEquals(expectedQuestion, actualQuestion);
    }

    @Test // тестирование удаления вопроса как объекта
    public void shouldRemoveQuestionAsObject() {
        // given
        init();
        Question expectedQuestion = new Question("Question?", "Answer");
        // when
        Question actualQuestion = questionService.remove(expectedQuestion);
        // then
        Assertions.assertEquals(expectedQuestion, actualQuestion);
    }

    @Test // тестирование удаления вопроса и уменьшение общего количества вопросов
    public void shouldRemoveQuestionAndDecreaseNumberOfQuestions() {
        // given
        init();
        Question questionToRemove = new Question("Question?", "Answer");
        int expectedAmountOfQuestions = 3;
        // when
        questionService.remove(questionToRemove);
        int actualAmountOfQuestions = questionService.getQuestionsCollectionSize();
        // then
        Assertions.assertEquals(expectedAmountOfQuestions, actualAmountOfQuestions);
    }

    @Test // тестирование возврата случайного вопроса
    public void shouldReturnRandomQuestion() {
        // given
        questionService.add("Question?", "Answer");
        Question expectedQuestion = new Question("Question?", "Answer");
        // when
        Question actualQuestion = questionService.getRandomQuestion();
        //then
        Assertions.assertEquals(expectedQuestion, actualQuestion);
    }

    @Test // проверка размера коллекции
    public void shouldReturnCollectionSize() {
        // given
        init();
        int expectedSize = 4;
        // when
        int actualSize = questionService.getQuestionsCollectionSize();
        //then
        Assertions.assertEquals(expectedSize, actualSize);

    }
}
