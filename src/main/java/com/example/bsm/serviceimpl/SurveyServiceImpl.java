package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Survey;
import com.example.bsm.entity.User;
import com.example.bsm.exception.SurveyNotFoundException;
import com.example.bsm.repository.SurveyRepository;
import com.example.bsm.request.SurveyRequest;
import com.example.bsm.response.SurveyResponse;
import com.example.bsm.security.AuthUtil;
import com.example.bsm.service.SurveyService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Service
public class SurveyServiceImpl implements SurveyService {

    private SurveyRepository surveyRepository;
    private AuthUtil authUtil;


    private SurveyResponse mapToSurveyResponse(Survey survey){
        return SurveyResponse.builder()
                .surveyId(survey.getSurveyId())
                .additives(survey.isAdditives())
                .consumedAlcohol(survey.isConsumedAlcohol())
                .medicines(survey.isMedicines())
                .consumedTobacco(survey.isConsumedTobacco())
                .preMedicalCondition(survey.isPreMedicalCondition())
                .build();
    }
    private Survey mapToSurvey(SurveyRequest surveyRequest, Survey survey) {
        survey.setAdditives(surveyRequest.isAdditives());
        survey.setConsumedAlcohol(surveyRequest.isConsumedAlcohol());
        survey.setMedicines(surveyRequest.isMedicines());
        survey.setConsumedTobacco(surveyRequest.isConsumedTobacco());
        survey.setPreMedicalCondition(surveyRequest.isPreMedicalCondition());
        return survey;
    }




    @Override
    public SurveyResponse addSurvey(SurveyRequest surveyRequest) {
        User user = authUtil.getCurrentUser();
      Survey survey = this.mapToSurvey(surveyRequest , new Survey());
      survey= surveyRepository.save(survey);
      return this.mapToSurveyResponse(survey);
    }

    @Override
    public SurveyResponse findSurveyById(int surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new SurveyNotFoundException("Survey not found"));
        return this.mapToSurveyResponse(survey);
    }


    @Override
    public SurveyResponse updateSurveyById(SurveyRequest surveyRequest , int surveyId) {
        Survey existingSurvey = surveyRepository.findById(surveyId) // Replace with dynamic ID retrieval
                .orElseThrow(() -> new SurveyNotFoundException("Survey not found"));
        Survey updatedSurvey = this.mapToSurvey(surveyRequest, existingSurvey);
        surveyRepository.save(updatedSurvey);
        return this.mapToSurveyResponse(updatedSurvey);
    }

}
