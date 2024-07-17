package pro.sky.java.coursework2.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.coursework2.domain.Question;
import pro.sky.java.coursework2.exceptions.NotEnoughQuestionsException;
import pro.sky.java.coursework2.service.impl.ExaminerServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionServiceMock;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test // тест на выброс исключения
    public void shouldThrow_NotEnoughQuestionsException_When_AskedForTooMuchQuestionsForExam() {
        // given
        int amount = 10; // если задать amount = 1, то тест не проходит, т.к. исключение не выбрасывается
        List<Question> testQuestions = List.of(
                new Question("Question1?", "Answer1"),
                new Question("Question2?", "Answer2"),
                new Question("Question3?", "Answer3")
        );
        // when
        when(questionServiceMock.getAll())
                .thenReturn(testQuestions);
        // then
        Assertions.assertThrows(NotEnoughQuestionsException.class,
                () -> examinerService.getQuestions(amount)
        );
    }

    @Test // тест на составление сета вопросов нужного размера
    public void shouldReturnRequestedAmountOfQuestions() {
        // given
        int amount = 2;
        List<Question> testQuestions = List.of(
                new Question("Question1?", "Answer1"),
                new Question("Question2?", "Answer2"),
                new Question("Question3?", "Answer3")
        );

        // when
        when(questionServiceMock.getAll())
                .thenReturn(testQuestions);

        when(questionServiceMock.getRandomQuestion())
                .thenReturn(
                        testQuestions.get(0),
                        testQuestions.get(0),
                        testQuestions.get(0),
                        testQuestions.get(1));


        Collection<Question> actualQuestions= examinerService.getQuestions(amount);

        // then
        Assertions.assertEquals(Set.of(testQuestions.get(0), testQuestions.get(1)), actualQuestions);
        verify(questionServiceMock, atLeast(4)).getRandomQuestion();

    }
}
