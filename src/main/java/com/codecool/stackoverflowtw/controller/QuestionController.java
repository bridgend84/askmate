package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.AllQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.Direction;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
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

    /*TODO Endpoints
    sorted ascending by name alphabetically
    sorted descending by name alphabetically
    sorted ascending by date when created
    sorted desc by date
    sorted asc by answer count
    sorted desc by ans c
    */
    @GetMapping("/sorted/name/{direction}")
    public List<AllQuestionDTO> getSortedQuestionsByName(@PathVariable Direction direction) {
        return null;
    }

    @GetMapping("/sorted/date/{direction}")
    public List<AllQuestionDTO> getSortedQuestionsByDate(@PathVariable Direction direction) {
        return null;
    }

    @GetMapping("/{id}")
    public AllQuestionDTO getQuestionById(@PathVariable int id) {
        return null;
    }

    @PostMapping("/")
    public int addNewQuestion(@RequestBody NewQuestionDTO question) {
        return 0;
    }

    @DeleteMapping("/{id}")
    public boolean deleteQuestionById(@PathVariable int id) {
        return false;
    }
}
