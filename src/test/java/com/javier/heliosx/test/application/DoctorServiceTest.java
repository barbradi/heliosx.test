package com.javier.heliosx.test.application;

import com.javier.heliosx.test.model.AnswersModel;
import com.javier.heliosx.test.model.DoctorResponseModel;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    public static final String DRINK_ALCOHOL_QUESTION_ID = "1";

    @ParameterizedTest(name = "answer to drink alcohol is {0}, doctor allowed should be {1}")
    @CsvSource(textBlock = """
        no, true, ''
        yes, false, you are alcoholic
""")
    void processAnswers(String answerToQuestionDrinkAlcohol, boolean expectedAllowed, String expectedExplanation) {

        // Given
        AnswersModel answersModel = new AnswersModel(
                "userId",
                "userEmail",
                "conditionId",
                "condition",
                Arrays.asList(
                        new AnswersModel.AnswerModel(DRINK_ALCOHOL_QUESTION_ID, answerToQuestionDrinkAlcohol),
                        new AnswersModel.AnswerModel("otherId", "otherAnswer")
                )
        );

        // When
        DoctorResponseModel doctorResponse = doctorService.processAnswers(answersModel);

        // Then
        assertThat(doctorResponse.allowed()).isEqualTo(expectedAllowed);
        assertThat(doctorResponse.explanation()).isEqualTo(expectedExplanation);
    }
}