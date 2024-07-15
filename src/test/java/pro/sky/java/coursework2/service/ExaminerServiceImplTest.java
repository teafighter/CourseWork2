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
        // when
        when(questionServiceMock.getAll().size())
                .thenReturn(2);
        // then
        Assertions.assertThrows(NotEnoughQuestionsException.class,
                () -> examinerService.getQuestions(amount)
        );
    }

    @Test // тест на составление сета вопросов нужного размера
    public void shouldReturnRequestedAmountOfQuestions() {
        // given
        when(questionServiceMock.getAll().size())
                .thenReturn(4);
        int amount = 1;
        Question testQuestion = new Question("Question", "Answer");
        when(questionServiceMock.getRandomQuestion())
                .thenReturn(testQuestion);
        examinerService.getQuestions(amount);
        verify(questionServiceMock, atLeast(amount)).getRandomQuestion();

    }
}
