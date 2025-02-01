package com.example.bsm.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyRequest {

    private boolean  preMedicalCondition;
    private boolean  consumedAlcohol;
    private boolean consumedTobacco;
    private boolean additives;
    private boolean medicines;
}
