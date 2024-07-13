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

//    @BeforeEach // добавим несколько вопросов, всего 4
//    public void initSet() {
//        questionServiceMock.add("Какой самый строгий модификатор доступа в Java?", "private");
//        questionServiceMock.add("Сколько бит занимает в памяти переменная типа long?", "64");
//        Question testQuestion = new Question("Какой принцип ООП предназначен для управления доступом к данным извне?", "Инкапсуляция");
//        questionServiceMock.add(testQuestion);
//        questionServiceMock.add("Question?", "Answer");
//    }

    @Test // тест на выброс исключения
    public void shouldThrow_NotEnoughQuestionsException_When_AskedForTooMuchQuestionsForExam() {
        // given
        int amount = 10; // если задать amount = 1, то тест не проходит, т.к. исключение не выбрасывается
        // when
        when(questionServiceMock.getQuestionsCollectionSize())
                .thenReturn(2);
        // then
        Assertions.assertThrows(NotEnoughQuestionsException.class,
                () -> examinerService.getQuestions(amount)
        );
    }

    @Test // тест на составление сета вопросов нужного размера
    public void shouldReturnRequestedAmountOfQuestions() {
        // given
        when(questionServiceMock.getQuestionsCollectionSize())
                .thenReturn(4);
        int amount = 1;
        Question testQuestion = new Question("Question", "Answer");
        when(questionServiceMock.getRandomQuestion())
                .thenReturn(testQuestion);
        examinerService.getQuestions(amount);
        verify(questionServiceMock, atLeast(amount)).getRandomQuestion();

    }
}
