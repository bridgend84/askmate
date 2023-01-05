package com.codecool.stackoverflowtw.controller.dto;

import java.util.Date;
import java.util.List;

public record SingleQuestionDTO(
        String name,
        String description,
        String username,
        List<String> answersDescription,
        List<Date> answerCreated
) {
}
