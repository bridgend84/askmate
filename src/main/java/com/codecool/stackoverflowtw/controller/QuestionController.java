package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.Direction;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.AllQuestionDTO;
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

   /* Fedaféle megoldás
   @GetMapping("/all/sorted/name/asc")
    public List<AllQuestionDTO> getAllQuestionsSortedByNameAsc() {
        return questionService.getAllQuestionsSortedByNameAsc();
    }
    @GetMapping("/all/sorted/name/desc")
    public List<AllQuestionDTO> getAllQuestionsSortedByNameDesc() {
        return questionService.getAllQuestionsSortedByNameDesc();
    }
    Rékaféle:
    */

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

    @GetMapping("/{id}")
    public AllQuestionDTO getQuestionById(@PathVariable int id) {
        return null;
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
