package com.javier.heliosx.test.infrastructure.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix = "heliosx")
public record QuestionsProperties(
        List<Question> questions
) {

    public static record Question(
            String id,
            String question
    ) {}
}
