package com.codecool.stackoverflowtw.dao.model;

import java.sql.Date;

public class Question {
    private int question_id;
    private int user_id;
    private String name;
    private String description;
    private Date created;

    public Question(int question_id, int user_id, String name, String description, Date created) {
        this.question_id = question_id;
        this.user_id = user_id;
        this.name = name;
        this.description = description;
        this.created = created;
    }
}
