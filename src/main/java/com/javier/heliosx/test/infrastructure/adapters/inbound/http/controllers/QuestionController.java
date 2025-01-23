package com.javier.heliosx.test.infrastructure.adapters.inbound.http.controllers;

import com.javier.heliosx.test.application.DoctorService;
import com.javier.heliosx.test.application.QuestionService;
import com.javier.heliosx.test.infrastructure.configuration.QuestionsProperties;
import com.javier.heliosx.test.model.AnswersModel;
import com.javier.heliosx.test.model.DoctorResponseModel;
import com.javier.heliosx.test.model.QuestionsModel;
import com.javier.heliosx.test.model.QuestionsModel.QuestionModel;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class QuestionController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private QuestionService questionsService;

    @GetMapping("/consultation/condition/{conditionId}")
    public QuestionsModel getQuestionsModel(
            @Parameter(
                    description = "The ID of the condition",
                    required = false,
                    example = "defaultConditionId"
            )
            @PathVariable(required = false) String conditionId){

        log.info("getting questions");
        return new QuestionsModel(
                "testConditionId",
                "testCondition",
                questionsService.getQuestions(conditionId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/consultation/condition")
    public DoctorResponseModel postAnswers(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User answers to condition", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AnswersModel.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "userId": "userId",
                                      "userEmail": "userEmail",
                                      "conditionId": "conditionId",
                                      "condition": "condition",
                                      "answers": [
                                        {
                                          "id": "1",
                                          "answer": "yes"
                                        },
                                        {
                                          "id": "otherId",
                                          "answer": "otherAnswer"
                                        }
                                      ]
                                    }
                                    
                                    """)))
            @RequestBody AnswersModel answersModel){

        log.info("posting answers");
        return doctorService.processAnswers(answersModel);
    }
}
