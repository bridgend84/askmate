package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.*;
import com.codecool.stackoverflowtw.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public List<AllQuestionDTO> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public SingleQuestionDTO getQuestionById(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/answers/{id}")
    public List<AnswerDTO> getAllAnswersById(@PathVariable int id) {
        return questionService.getAllAnswerByQuestionId(id);
    }

    @GetMapping("/sorted/name/{direction}")
    public List<AllQuestionDTO> getSortedQuestionsByName(@PathVariable Direction direction) {
        if(direction.equals(Direction.ASC)){
            return questionService.getAllQuestionsSortedByNameAsc();
        } else {
            return questionService.getAllQuestionsSortedByNameDesc();
        }
    }
    @GetMapping("/sorted/date/{direction}")
    public List<AllQuestionDTO> getSortedQuestionsByDate(@PathVariable Direction direction) {
        if(direction.equals(Direction.ASC)){
            return questionService.getAllQuestionsSortedByDateAsc();
        } else {
            return questionService.getAllQuestionsSortedByDateDesc();
        }
    }

    @GetMapping("/sorted/answers-count/{direction}")
    public List<AllQuestionDTO> getSortedQuestionsByAnswerCount(@PathVariable Direction direction) {
        if(direction.equals(Direction.ASC)){
            return questionService.getAllQuestionsSortedByAnswersAsc();
        } else {
            return questionService.getAllQuestionsSortedByAnswerDesc();
        }
    }

    @PostMapping("/newanswer/{id}")
    public void addNewAnswer(@RequestBody NewAnswerDTO answer, @PathVariable int id) {
        questionService.addNewAnswer(answer, id);
    }

    @PostMapping("/")
    public void addNewQuestion(@RequestBody NewQuestionDTO question) {
        questionService.addNewQuestion(question);
    }


    @DeleteMapping("/{id}")
    public boolean deleteQuestionById(@PathVariable int id) {
        return false;
    }
}
