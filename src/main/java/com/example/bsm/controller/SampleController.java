package com.example.bsm.controller;

import com.example.bsm.entity.Sample;
import com.example.bsm.service.SampleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/samples")
public class SampleController {

    private final SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @PostMapping
    public ResponseEntity<Sample> createSample(@RequestBody Sample sample) {
        return ResponseEntity.ok(sampleService.createSample(sample));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sample> getSampleById(@PathVariable int id) {
        return ResponseEntity.ok(sampleService.getSampleById(id));
    }

    @GetMapping
    public ResponseEntity<List<Sample>> getAllSamples() {
        return ResponseEntity.ok(sampleService.getAllSamples());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sample> updateSample(@PathVariable int id, @RequestBody Sample sample) {
        return ResponseEntity.ok(sampleService.updateSample(id, sample));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSample(@PathVariable int id) {
        sampleService.deleteSample(id);
        return ResponseEntity.noContent().build();
    }
}
