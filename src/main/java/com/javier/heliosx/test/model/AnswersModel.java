package com.javier.heliosx.test.model;

import java.util.List;

public record AnswersModel(
        String userId,
        String userEmail,
        String conditionId,
        String condition,
        List<AnswerModel> answers
) {
    public record AnswerModel(
            String id,
            String answer
    ) {
    }
}


