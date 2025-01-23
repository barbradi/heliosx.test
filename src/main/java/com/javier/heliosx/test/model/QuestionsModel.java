package com.javier.heliosx.test.model;

import java.util.List;

public record QuestionsModel(
        String conditionId,
        String condition,
        List<QuestionModel> questions
) {
    public record QuestionModel(
            String id,
            String question
    ) {
    }
}

