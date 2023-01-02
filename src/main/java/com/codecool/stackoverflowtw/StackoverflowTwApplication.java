package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDaoJdbc;
import com.codecool.stackoverflowtw.database.Database;
import com.codecool.stackoverflowtw.initialise.TableInitializer;
import com.codecool.stackoverflowtw.initialise.TableStatements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class StackoverflowTwApplication {

    public static void main(String[] args) {
        //Init tables
        Database database = new Database(
                "jdbc:postgresql://localhost:5432/askmate",
                "postgres",
                "");
        Map<String, String> tables = Map.of(
                "users", TableStatements.USERS,
                "questions", TableStatements.QUESTIONS,
                "answer", TableStatements.ANSWER,
                "tags", TableStatements.TAGS,
                "question_tags", TableStatements.QUESTION_TAGS
        );
        TableInitializer tableInitializer = new TableInitializer(database, tables);
        tableInitializer.initialize();

        SpringApplication.run(StackoverflowTwApplication.class, args);
    }

    @Bean
    public QuestionsDAO questionsDAO() {
        return new QuestionsDaoJdbc();
    }
}
