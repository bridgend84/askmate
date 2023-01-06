package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.AllQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.SingleQuestionDTO;
import com.codecool.stackoverflowtw.service.QuestionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface QuestionsDAO {
    List<AllQuestionDTO> getAllQuestions();
    List<AllQuestionDTO> getAllQuestionsSortedByNameAsc();
    List<AllQuestionDTO> getAllQuestionsSortedByNameDesc();
    List<AllQuestionDTO> getAllQuestionsSortedByDateAsc();
    List<AllQuestionDTO> getAllQuestionsSortedByDateDesc();
    List<AllQuestionDTO> getAllQuestionsSortedByAnswersAsc();
    List<AllQuestionDTO> getAllQuestionsSortedByAnswerDesc();
    void addNewQuestion(NewQuestionDTO question);
    SingleQuestionDTO getQuestionById(int id);
    void deleteQuestionsById(int id);
}
