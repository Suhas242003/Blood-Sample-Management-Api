package com.example.bsm.service;


import com.example.bsm.entity.Sample;

import java.util.List;

public interface SampleService {

    Sample createSample(Sample sample);

    Sample getSampleById(int sampleId);

    List<Sample> getAllSamples();

    Sample updateSample(int sampleId, Sample sample);

    void deleteSample(int sampleId);
}
