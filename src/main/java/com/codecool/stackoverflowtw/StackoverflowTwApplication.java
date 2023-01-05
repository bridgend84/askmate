package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDaoJdbc;
import com.codecool.stackoverflowtw.database.Database;
import com.codecool.stackoverflowtw.initialise.TableInitializer;
import com.codecool.stackoverflowtw.initialise.TableStatements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class StackoverflowTwApplication {
    @Autowired
    private Database database;

    public static void main(String[] args) {
        //Init tables
//        Database database = new Database(
//                "jdbc:postgresql://localhost:5432/askmate",
//                "postgres",
//                "");
        Map<String, String> table = new LinkedHashMap<>();
        table.put("users", TableStatements.USERS);
        table.put("questions", TableStatements.QUESTIONS);
        table.put("answer", TableStatements.ANSWER);
        table.put("tags", TableStatements.TAGS);
        table.put("question_tags", TableStatements.QUESTION_TAGS);

        //TableInitializer tableInitializer = new TableInitializer(database, table);
        //tableInitializer.initialize();

        SpringApplication.run(StackoverflowTwApplication.class, args);
    }
    @Bean
    public QuestionsDAO questionsDAO() {
        return new QuestionsDaoJdbc(database);
    }
}
