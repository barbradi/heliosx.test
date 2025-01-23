package com.javier.heliosx.test.application;

import com.javier.heliosx.test.model.AnswersModel;
import com.javier.heliosx.test.model.DoctorResponseModel;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    public DoctorResponseModel processAnswers(AnswersModel answers){
        Pair<Boolean, String> doctorResponse = isUserAllowed(answers);
        return new DoctorResponseModel(
                answers.userId(),
                answers.userEmail(),
                answers.conditionId(),
                answers.condition(),
                doctorResponse.getLeft(),
                doctorResponse.getRight()
        );
    }

    private Pair<Boolean, String> isUserAllowed(AnswersModel answers) {
        boolean allowed;
        String explanation;
        if (findAnswerById(answers, "1").equals("yes")){
            allowed = false;
            explanation = "you are alcoholic";
        }
        else {
            allowed = true;
            explanation = "";
        }
        return new ImmutablePair<>(allowed, explanation);
    }

    private String findAnswerById(AnswersModel answers, String id){
        return answers.answers().stream()
                .filter(a -> a.id().equals(id))
                .findFirst()
                .get()
                .answer();
    }
}
