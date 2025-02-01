package com.example.bsm.service;

import com.example.bsm.request.SurveyRequest;
import com.example.bsm.response.SurveyResponse;


public interface SurveyService {

    SurveyResponse addSurvey(SurveyRequest surveyRequest);

    SurveyResponse findSurveyById(int surveyId);

    SurveyResponse updateSurveyById(SurveyRequest surveyRequest, int surveyId);
}
