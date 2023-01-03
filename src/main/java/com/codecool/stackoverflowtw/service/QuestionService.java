package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.AllQuestionDTO;
import com.codecool.stackoverflowtw.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    private AllQuestionDTO toAllQuestionDTORecord(ResultSet resultSet) throws SQLException {
        return new AllQuestionDTO(
                resultSet.getString("name"),
                resultSet.getDate("created"),
                resultSet.getInt("answerCount")
        );
    }

    public List<AllQuestionDTO> getAllQuestions() {
        // TODO
        Database database = new Database(
                "jdbc:postgresql://localhost:5432/askmate",
                "postgres",
                "");

        String sql = """
                SELECT questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY name, questions.name, questions.created;
                """;

        try (
                Connection connection = database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            List<AllQuestionDTO> allQuestions = new ArrayList<>();
            while (resultSet.next()) {
                AllQuestionDTO question = toAllQuestionDTORecord(resultSet);
                allQuestions.add(question);
            }
            System.out.println(allQuestions);
            return allQuestions;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
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

    public int addNewQuestion(NewQuestionDTO question) {
        // TODO
        int createdId = 0;
        return createdId;
    }
}
