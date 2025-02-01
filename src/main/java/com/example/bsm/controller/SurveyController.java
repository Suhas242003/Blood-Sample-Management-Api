package com.example.bsm.controller;
import com.example.bsm.request.SurveyRequest;
import com.example.bsm.response.SurveyResponse;
import com.example.bsm.service.SurveyService;
import com.example.bsm.utility.ResponseStructure;
import com.example.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class SurveyController {
    private final SurveyService surveyService;
    private  final RestResponseBuilder responseBuilder;
    @PostMapping("/add-survey")
    public ResponseEntity<ResponseStructure<SurveyResponse>> addSurvey(@RequestBody @Valid SurveyRequest surveyRequest){
        SurveyResponse surveyResponse = surveyService.addSurvey(surveyRequest);
        return responseBuilder.success(HttpStatus.CREATED, "User Created", surveyResponse);
    }
    @GetMapping("/survey/{surveyId}")
    public ResponseEntity<ResponseStructure<SurveyResponse>> findSurveyById(@PathVariable int surveyId ){
       SurveyResponse surveyResponse= surveyService.findSurveyById(surveyId);
        return responseBuilder.success(HttpStatus.FOUND, "User Found", surveyResponse);
    }
    @PutMapping("/survey")
    public ResponseEntity<ResponseStructure<SurveyResponse>> updateSurvey(@RequestBody @Valid SurveyRequest surveyRequest,int surveyId){
        SurveyResponse surveyResponse = surveyService.updateSurveyById(surveyRequest,surveyId);
        return responseBuilder.success(HttpStatus.OK, "User updated", surveyResponse);
    }
}
