package pro.sky.java.coursework2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Недостаточно вопросов в списке доступных")
public class NotEnoughQuestionsException extends RuntimeException {

    public NotEnoughQuestionsException() {
        super();
    }

    public NotEnoughQuestionsException(String message) {
        super(message);
    }

    public NotEnoughQuestionsException(String message, Throwable t) {
        super(message, t);
    }

    public NotEnoughQuestionsException(Throwable t) {
        super(t);
    }
}