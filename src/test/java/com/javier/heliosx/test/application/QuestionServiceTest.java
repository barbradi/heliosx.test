package com.javier.heliosx.test.application;

import com.javier.heliosx.test.infrastructure.configuration.QuestionsProperties;
import com.javier.heliosx.test.model.QuestionsModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionsProperties questionsProperties;

    @Test
    void getQuestions() {
        // Given
        QuestionsProperties.Question q1 =  new QuestionsProperties.Question("id1", "question1");
        QuestionsProperties.Question q2 =  new QuestionsProperties.Question("id2", "question2");
        when(questionsProperties.questions()).thenReturn(Arrays.asList(q1,q2));


        // When
        List<QuestionsModel.QuestionModel> questionModel = questionService.getQuestions("aConditionId");

        // Then
        assertThat(questionModel).hasSize(2);
        assertThat(questionModel)
                .extracting(q -> q.id())
                .containsExactlyInAnyOrder("id1", "id2");
    }
}