package pro.sky.java.coursework2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.coursework2.domain.Question;
import pro.sky.java.coursework2.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java/")
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return questionService.getAll();
    }

    @GetMapping(path = "/add")
    public Question addQuestion(
            @RequestParam(name = "question") String question,
            @RequestParam(name = "answer") String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping(path = "/remove")
    public Question removeQuestion(
            @RequestParam(name = "QuestionText") String question,
            @RequestParam(name = "QuestionAnswer") String answer) {
        return questionService.remove(new Question(question, answer));
    }


}
