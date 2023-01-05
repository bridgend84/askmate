package com.codecool.stackoverflowtw.controller.dto;

import java.sql.Date;

public record AllQuestionDTO(
        int id,
        String name,
        Date created,
        int answerCount) {
}
