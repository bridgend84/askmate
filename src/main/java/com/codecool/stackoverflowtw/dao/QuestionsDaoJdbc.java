package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.AllQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.database.Database;
import com.codecool.stackoverflowtw.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoJdbc implements QuestionsDAO {
    private Database database;
    @Autowired
    public QuestionsDaoJdbc(Database database) {
        this.database = database;
    }

    public List<AllQuestionDTO> queryController(String sql) {
        try (
                Connection connection = database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            List<AllQuestionDTO> allQuestions = new ArrayList<>();
            while (resultSet.next()) {
                AllQuestionDTO question = toAllQuestionDTORecord(resultSet);
                allQuestions.add(question);
            }
            return allQuestions;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private AllQuestionDTO toAllQuestionDTORecord(ResultSet resultSet) throws SQLException {
        return new AllQuestionDTO(
                resultSet.getString("name"),
                resultSet.getDate("created"),
                resultSet.getInt("answerCount")
        );
    }

    public List<AllQuestionDTO> getAllQuestions() {
        String sql = """
                SELECT questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY name, questions.name, questions.created;
                """;
        return queryController(sql);
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByNameAsc() {
        String sql = """
                SELECT questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY name, questions.name, questions.created
                ORDER BY questions.name ASC;
                """;

        return queryController(sql);
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByNameDesc() {
        String sql = """
                SELECT questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY name, questions.name, questions.created
                ORDER BY questions.name DESC;
                """;

        return queryController(sql);
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByDateAsc() {
        String sql = """
                SELECT questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY name, questions.name, questions.created
                ORDER BY questions.created ASC;
                """;
        return queryController(sql);
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByDateDesc() {
        String sql = """
                SELECT questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY name, questions.name, questions.created
                ORDER BY questions.created DESC;
                """;
        return queryController(sql);
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByAnswersAsc() {
        String sql = """
                SELECT questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY name, questions.name, questions.created
                ORDER BY answerCount ASC;
                """;
        return queryController(sql);
    }

    public List<AllQuestionDTO> getAllQuestionsSortedByAnswerDesc() {
        String sql = """
                SELECT questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY name, questions.name, questions.created
                ORDER BY answerCount DESC;
                """;
        return queryController(sql);
    }

    public void addNewQuestion(NewQuestionDTO question) {
        String template = "INSERT INTO public.questions (user_id, name, description, created) VALUES (1, ?, ?, current_timestamp);";
        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement(template)) {
            prepare(question, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepare(NewQuestionDTO newQuestionDTO, PreparedStatement statement) throws SQLException {
        statement.setString(1, newQuestionDTO.name());
        statement.setString(2, newQuestionDTO.description());
    }
}
