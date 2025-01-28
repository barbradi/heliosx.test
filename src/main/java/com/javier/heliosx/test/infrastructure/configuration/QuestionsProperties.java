package com.javier.heliosx.test.infrastructure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
