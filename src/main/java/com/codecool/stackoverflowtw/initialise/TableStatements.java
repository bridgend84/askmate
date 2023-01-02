package com.codecool.stackoverflowtw.initialise;

public interface TableStatements {

//    DROP TABLE IF EXISTS question_tags;
//    DROP TABLE IF EXISTS tags;
//    DROP TABLE IF EXISTS answer;
//    DROP TABLE IF EXISTS questions;
//    DROP TABLE IF EXISTS users;

   String USERS = """
           CREATE TABLE users
                       (
                               user_id  serial PRIMARY KEY,
                               username VARCHAR(50) UNIQUE NOT NULL
           );
           """;

    String QUESTIONS = """
            CREATE TABLE questions
                        (
                                question_id serial PRIMARY KEY,
                                user_id     serial              NOT NULL,
                                name        VARCHAR(255) UNIQUE NOT NULL,
                description VARCHAR(255)        NOT NULL,
                created     TIMESTAMP           NOT NULL,
                CONSTRAINT fk_user
                FOREIGN KEY (user_id)
                REFERENCES users (user_id)
            );
            """;

    String ANSWER = """
            CREATE TABLE answer
                        (
                                answer_id   serial PRIMARY KEY,
                                question_id serial       NOT NULL,
                                description VARCHAR(255) NOT NULL,
                created     TIMESTAMP    NOT NULL,
                CONSTRAINT fk_questions
                FOREIGN KEY (question_id)
                REFERENCES questions (question_id)
            );
            """;

    String TAGS = """
            CREATE TABLE tags
                        (
                                tag_id serial PRIMARY KEY,
                                name   VARCHAR(50) UNIQUE NOT NULL
            );
            """;


    String QUESTION_TAGS = """
            CREATE TABLE question_tags
                        (
                                tag_id      serial,
                                question_id serial,
                                CONSTRAINT fk_questions
                    FOREIGN KEY (question_id)
                REFERENCES questions (question_id),
                CONSTRAINT fk_tags
                FOREIGN KEY (tag_id)
                REFERENCES tags (tag_id)
            );
            """;

}
