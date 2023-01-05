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

    private SingleQuestionDTO queryControllerPlusPlus(String sql) {
        try (
                Connection connection = database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            SingleQuestionDTO singleQuestion = toSingleQuestionDTORecord(resultSet);
            return singleQuestion;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }



    private SingleQuestionDTO toSingleQuestionDTORecord(ResultSet resultSet) throws SQLException {
        return new SingleQuestionDTO(

                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getString("username")
        );
    }

    public List<AllQuestionDTO> getAllQuestions() {
        String sql = """
                SELECT questions.question_id, questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY questions.question_id, name, questions.name, questions.created;
                """;
        return queryController(sql);
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByNameAsc() {
        String sql = """
                SELECT questions.question_id, questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY questions.question_id, name, questions.name, questions.created
                ORDER BY questions.name ASC;
                """;

        return queryController(sql);
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByNameDesc() {
        String sql = """
                SELECT questions.question_id, questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY questions.question_id, name, questions.name, questions.created
                ORDER BY questions.name DESC;
                """;

        return queryController(sql);
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByDateAsc() {
        String sql = """
                SELECT questions.question_id, questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY questions.question_id, name, questions.name, questions.created
                ORDER BY questions.created ASC;
                """;
        return queryController(sql);
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByDateDesc() {
        String sql = """
                SELECT questions.question_id, questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY questions.question_id, name, questions.name, questions.created
                ORDER BY questions.created DESC;
                """;
        return queryController(sql);
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByAnswersAsc() {
        String sql = """
                SELECT questions.question_id, questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY questions.question_id, name, questions.name, questions.created
                ORDER BY answerCount ASC;
                """;
        return queryController(sql);
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByAnswerDesc() {
        String sql = """
                SELECT questions.question_id, questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY questions.question_id, name, questions.name, questions.created
                ORDER BY answerCount DESC;
                """;
        return queryController(sql);
    }

    public SingleQuestionDTO getQuestionById(int id) {
        String sql = "SELECT questions.name, questions.description, u.username, a.description, a.created FROM questions FULL JOIN answer a on questions.question_id = a.question_id FULL JOIN users u on questions.user_id = u.user_id WHERE a.question_id == " + id + ";";
        System.out.println(id);
        return queryControllerPlusPlus(sql);
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
}
