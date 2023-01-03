package com.codecool.stackoverflowtw.controller.dto;

public record AllQuestionDTO(
        String name,
        java.sql.Date created,
        int answerCount) {
}
