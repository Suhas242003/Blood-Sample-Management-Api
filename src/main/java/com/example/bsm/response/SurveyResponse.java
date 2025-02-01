package com.example.bsm.response;

import lombok.Builder;

@Builder
public class SurveyResponse {
    private int surveyId;
    private boolean  preMedicalCondition;
    private boolean  consumedAlcohol;
    private boolean consumedTobacco;
    private boolean additives;
    private boolean medicines;
}
