package com.example.bsm.request;

import com.example.bsm.enums.BloodGroup;
import lombok.Data;

@Data
public class SampleRequest {

    private BloodGroup bloodGroup;
    private int quantity;
    private boolean availability;
    private int emergencyUnits;
    private int availableUnits;

}
