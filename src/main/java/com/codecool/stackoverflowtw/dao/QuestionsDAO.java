package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.*;
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
    List<AnswerDTO> getAllAnswerByQuestionId(int id);

    void addNewAnswer(NewAnswerDTO answer, int id);
}
