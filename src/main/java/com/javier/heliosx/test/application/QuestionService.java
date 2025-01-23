package com.javier.heliosx.test.application;

import com.javier.heliosx.test.infrastructure.configuration.QuestionsProperties;
import com.javier.heliosx.test.model.QuestionsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionsProperties questionsProperties;

    public List<QuestionsModel.QuestionModel> getQuestions(String conditionId) {
        return questionsProperties.questions()
                .stream()
                .map(question -> new QuestionsModel.QuestionModel(
                        String.valueOf(question.id()), // Convert ID to String if needed
                        question.question()
                ))
                .collect(Collectors.toList());
    }
}
