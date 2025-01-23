package com.javier.heliosx.test.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javier.heliosx.test.model.AnswersModel;
import com.javier.heliosx.test.model.DoctorResponseModel;
import com.javier.heliosx.test.model.QuestionsModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class QuestionsIntegrationTest extends IntegrationTestSetup {

    public static final String DRINK_ALCOHOL_QUESTION_ID = "1";

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getQuestions(){
        QuestionsModel questions = webTestClient.get()
                .uri("/consultation/condition/testCondition")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(QuestionsModel.class)
                .returnResult()
                .getResponseBody();

        assertThat(questions).isNotNull();
        assertThat(questions.questions()).hasSize(3);
        assertThat(questions.questions()).hasSize(3);

    }

    @Test
    void getDoctorAnswer() throws JsonProcessingException {
        AnswersModel answersModel = new AnswersModel(
                "userId",
                "userEmail",
                "conditionId",
                "condition",
                Arrays.asList(
                        new AnswersModel.AnswerModel(DRINK_ALCOHOL_QUESTION_ID, "yes"),
                        new AnswersModel.AnswerModel("otherId", "otherAnswer")
                )
        );

        DoctorResponseModel doctorResponse = webTestClient.post()
                .uri("/consultation/condition")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(answersModel)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(DoctorResponseModel.class)
                .returnResult()
                .getResponseBody();

        assertThat(doctorResponse).isNotNull();
        assertThat(doctorResponse.allowed()).isFalse();
        assertThat(doctorResponse.explanation()).isEqualTo("you are alcoholic");
    }
}
