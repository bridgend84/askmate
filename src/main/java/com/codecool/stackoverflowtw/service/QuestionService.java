package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.SingleQuestionDTO;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.AllQuestionDTO;
import com.codecool.stackoverflowtw.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    private QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    /*public QuestionDTO getQuestionById(int id) {
        // TODO
        questionsDAO.sayHi();
        return new QuestionDTO(id, "example title", "example desc", LocalDateTime.now());
    }*/

    public boolean deleteQuestionById(int id) {
        // TODO
        return false;
    }

//    public int addNewQuestion(NewQuestionDTO question) {
//        // TODO
//        String sql = """
//                INSERT INTO questions(name, description, created)
//                VALUES (?,?,?);
//                """
//        int createdId = 0;
//        return createdId;
//    }

    public List<AllQuestionDTO> getAllQuestions() {
        return questionsDAO.getAllQuestions();
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByNameAsc() {
        return questionsDAO.getAllQuestionsSortedByNameAsc();
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByNameDesc() {
        return questionsDAO.getAllQuestionsSortedByNameDesc();
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByDateAsc() {
        return questionsDAO.getAllQuestionsSortedByDateAsc();
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByDateDesc() {
        return questionsDAO.getAllQuestionsSortedByDateDesc();
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByAnswersAsc() {
        return questionsDAO.getAllQuestionsSortedByAnswersAsc();
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByAnswerDesc() {
        return questionsDAO.getAllQuestionsSortedByAnswerDesc();
    }

    public void addNewQuestion(NewQuestionDTO question) {
        questionsDAO.addNewQuestion(question);
    }

    public SingleQuestionDTO getQuestionById(int id) {
        return questionsDAO.getQuestionById(id);
    }
}
