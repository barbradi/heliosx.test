package com.javier.heliosx.test.model;

public record DoctorResponseModel(
        String userId,
        String userEmail,
        String conditionId,
        String condition,
        boolean allowed,
        String explanation
) {
}
