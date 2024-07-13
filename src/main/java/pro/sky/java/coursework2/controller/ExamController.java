package pro.sky.java.coursework2.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.java.coursework2.domain.Question;
import pro.sky.java.coursework2.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/get")
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping(path = "/{amount}")
    public Collection<Question> getQuestions(@PathVariable("amount") @RequestParam(name = "amount") int amount) {
        return examinerService.getQuestions(amount);
    }
}
