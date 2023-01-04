package com.codecool.stackoverflowtw.controller.dto;

import java.sql.Date;

public record AllQuestionDTO(
        String name,
        Date created,
        int answerCount) {
}
