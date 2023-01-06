package com.codecool.stackoverflowtw.controller.dto;

import java.util.Date;

public record AnswerDTO (
        int questionId,
        String description,
        Date created
){}
