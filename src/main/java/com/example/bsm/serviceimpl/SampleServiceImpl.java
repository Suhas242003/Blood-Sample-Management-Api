package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Sample;
import com.example.bsm.repository.SampleRepository;
import com.example.bsm.service.SampleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {

    private final SampleRepository sampleRepository;

    public SampleServiceImpl(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Sample createSample(Sample sample) {
        return sampleRepository.save(sample);
    }

    @Override
    public Sample getSampleById(int sampleId) {
        return sampleRepository.findById(sampleId) .orElseThrow(() ->
                new RuntimeException("Sample not found with id: " + sampleId));
    }

    @Override
    public List<Sample> getAllSamples() {
        return sampleRepository.findAll();
    }

    @Override
    public Sample updateSample(int sampleId, Sample sample) {
        Sample existingSample = getSampleById(sampleId);
        existingSample.setBloodGroup(sample.getBloodGroup());
        existingSample.setQuantity(sample.getQuantity());
        existingSample.setAvailability(sample.isAvailability());
        existingSample.setEmergencyUnits(sample.getEmergencyUnits());
        existingSample.setAvailableUnits(sample.getAvailableUnits());
        return sampleRepository.save(existingSample);
    }

    @Override
    public void deleteSample(int sampleId) {
        sampleRepository.deleteById(sampleId);
    }
}
