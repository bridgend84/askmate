package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.*;
import com.codecool.stackoverflowtw.database.Database;
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
                resultSet.getInt("question_id"),
                resultSet.getString("name"),
                resultSet.getDate("created"),
                resultSet.getInt("answerCount")
        );
    }

    public List<AllQuestionDTO> getAllQuestions() {
        String sql = """
                SELECT questions.question_id, questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY name, questions.name, questions.created, questions.question_id;
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

    public List<AllQuestionDTO> getAllQuestionsSortedByDate(String order) {
        String sql = """
                SELECT questions.question_id, questions.name, questions.created, COUNT(answer.question_id) AS answerCount
                FROM questions
                         FULL JOIN answer ON questions.question_id = answer.question_id
                GROUP BY questions.question_id, name, questions.name, questions.created
                ORDER BY questions.created 
                """ + order + ";";
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

    private SingleQuestionDTO queryControllerPlusPlus(String sql) {
        try (
                Connection connection = database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            List<SingleQuestionDTO> allQuestions = new ArrayList<>();
            while (resultSet.next()) {
                SingleQuestionDTO question = toSingleQuestionDTORecord(resultSet);
                allQuestions.add(question);
            }
            return allQuestions.get(0);
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

    public SingleQuestionDTO getQuestionById(int id) {
        String sql =
                "SELECT questions.name, questions.description, users.username " +
                        "FROM questions " +
                        "FULL JOIN users on questions.user_id = users.user_id WHERE questions.question_id = "
                        + id + ";";
        System.out.println(id);
        return queryControllerPlusPlus(sql);
    }

    public List<AnswerDTO> queryControllerForAnswers(String sql) {
        try (
                Connection connection = database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            List<AnswerDTO> allAnswers = new ArrayList<>();
            while (resultSet.next()) {
                AnswerDTO question = toAllAnswerByQuestionIdDTORecord(resultSet);
                allAnswers.add(question);
            }
            return allAnswers;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private AnswerDTO toAllAnswerByQuestionIdDTORecord(ResultSet resultSet) throws SQLException {
        return new AnswerDTO(
                resultSet.getInt("question_id"),
                resultSet.getString("description"),
                resultSet.getDate("created")
        );
    }

    @Override
    public List<AnswerDTO> getAllAnswerByQuestionId(int id) {
        String sql = "SELECT answer.question_id, answer.description, answer.created FROM answer WHERE answer.question_id = " + id + ";";
        return queryControllerForAnswers(sql);
    }

    @Override
    public void addNewAnswer(NewAnswerDTO answer, int id) {
        String template = "INSERT INTO public.answer ( question_id, description, created) VALUES (" + id + ", ?, current_timestamp);";
        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement(template)) {
            prepareAnswer(answer, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepareAnswer(NewAnswerDTO newAnswerDTO, PreparedStatement statement) throws SQLException {
        statement.setString(1, newAnswerDTO.description());
    }


    @Override
    public void deleteQuestionsById(int id) {
        String template = "DELETE FROM questions WHERE question_id = " + id + ";";
        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement(template)) {
//            prepare(question, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
